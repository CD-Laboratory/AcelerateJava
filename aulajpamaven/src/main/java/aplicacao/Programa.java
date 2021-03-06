package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

// inserir dados na base
public class Programa {

	public static void main(String[] args) {
		
		// o id � nulo pois o proprio banco de dados o gera
		Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmal.com");
		Pessoa p2 = new Pessoa(null, "Ana Maria", "ana@gmal.com");
		Pessoa p3 = new Pessoa(null, "Osvald Prosper", "osvaldo@gmal.com");
		
		/*aqui criamos uma instancia do entity manage factory e 
		* passamos para ela o nome da persistence unit que criamos em persistence.xml
		*/
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = enf.createEntityManager();
		
		//iniciar transacao no banco de dados
		em.getTransaction().begin();
		
		// inserindo os objetos no banco de dados
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		
		//encerro a transacao no banco de dados
		em.getTransaction().commit();
		
		System.out.println("Finalizou!!");
		
		//fechar os entitys
		em.close();
		enf.close();
	}
	
}
