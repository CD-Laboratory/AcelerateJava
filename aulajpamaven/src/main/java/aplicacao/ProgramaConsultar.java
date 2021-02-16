package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

//consultar dados na base
public class ProgramaConsultar {

	public static void main(String[] args) {
		
		/*aqui criamos uma instancia do entity manage factory e 
		* passamos para ela o nome da persistence unit que criamos em persistence.xml
		*/
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = enf.createEntityManager();
		
		//iniciar transacao no banco de dados
		em.getTransaction().begin();
		
		//consultar pessoa no banco de dados
		Pessoa p = em.find(Pessoa.class, 2);
		
		System.out.println(p);
				
		System.out.println("Pronto, terminou!!");
		
		//fechar os entitys
		em.close();
		enf.close();

	}

}
