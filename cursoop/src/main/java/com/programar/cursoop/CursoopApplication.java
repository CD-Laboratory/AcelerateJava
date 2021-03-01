package com.programar.cursoop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.programar.cursoop.domain.Categoria;
import com.programar.cursoop.domain.Cidade;
import com.programar.cursoop.domain.Cliente;
import com.programar.cursoop.domain.Endereco;
import com.programar.cursoop.domain.Estado;
import com.programar.cursoop.domain.Produto;
import com.programar.cursoop.domain.enums.TipoCliente;
import com.programar.cursoop.repositories.CategoriaRepository;
import com.programar.cursoop.repositories.CidadeRepository;
import com.programar.cursoop.repositories.ClienteRepository;
import com.programar.cursoop.repositories.EnderecoRepository;
import com.programar.cursoop.repositories.EstadoRepository;
import com.programar.cursoop.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoopApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoopApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//associar as categorias aos produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associar os produtos as categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//salvar as categorias e produtos no repositorio (banco de dados)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); 
		
		//definie os estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//definir as cidades
		Cidade c1 = new Cidade(null, "Uberlãndia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//associar os estados as cidades
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		//salvar os estados e as cidade no repositorio (banco de dados)
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//definir os clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "38777012", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		//definir endereco - o endereco ja esta associado ao cliente e a cidade
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		//associar o cliente ao endereco
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//salvar os clientes e estados no repositorio (banco de dados)
		clienteepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
