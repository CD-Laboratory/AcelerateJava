package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class ProgramaExcluir {

	public static void main(String[] args) {

		/*aqui criamos uma instancia do entity manage factory e 
		* passamos para ela o nome da persistence unit que criamos em persistence.xml
		*/
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = enf.createEntityManager();
	
		//realizar a monitoracao do objeto, para que o mesmo possa ser removido
		Pessoa p = em.find(Pessoa.class, 2);
		
		//iniciar transacao no banco de dados
		em.getTransaction().begin();
		
		//remover objeto do banco de dados
	    em.remove(p);
	    
	    //commitando a transacao
	    em.getTransaction().commit();
				
		System.out.println("Pronto, pessoa excluida do banco de dados!!");
		
		//fechar os entitys
		em.close();
		enf.close();
	}

}
