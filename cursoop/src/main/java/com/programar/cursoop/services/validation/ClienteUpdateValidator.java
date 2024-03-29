package com.programar.cursoop.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.programar.cursoop.domain.Cliente;
import com.programar.cursoop.domain.enums.TipoCliente;
import com.programar.cursoop.dto.ClienteDTO;
import com.programar.cursoop.repositories.ClienteRepository;
import com.programar.cursoop.resources.exceptions.FieldMessage;
import com.programar.cursoop.services.validation.utils.BR;

//Cria uma anotação personalizada - neste caso uma validacao para cpf e cnpj que vem de um unico campo
//ConstraintValidator<Nome da classe que voece esta auxiliando , o tipo da classe que vai receber a anotacao>
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	//metodo de inicializacao
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	//metodo para validacao
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		// pega o id passado na URI
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// verifica se o email existe
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
        // aqui ele vai percorrer a lista que contem os erros do framework, para apresentar a mensagem de acordo com o erro
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}