package com.programar.cursoop.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.programar.cursoop.domain.enums.TipoCliente;
import com.programar.cursoop.dto.ClienteNewDTO;
import com.programar.cursoop.resources.exceptions.FieldMessage;
import com.programar.cursoop.services.validation.utils.BR;

//Cria uma anotação personalizada - neste caso uma validacao para cpf e cnpj que vem de um unico campo
//ConstraintValidator<Nome da classe que voece esta auxiliando , o tipo da classe que vai receber a anotacao>
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	//meto de inicializacao
	@Override
	public void initialize(ClienteInsert ann) {
	}

	//metodo para validacao
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// aqui coloco as validações dos campos, se acontecer algum erro eu crio a mensagem personalizada pra inserir no fieldMessage
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOucnpj())) {
			list.add(new FieldMessage("cpfOucnpj", "CPF Inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOCAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOucnpj())) {
			list.add(new FieldMessage("cpfOucnpj", "CNPJ Inválido"));
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