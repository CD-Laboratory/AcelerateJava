package aplicacao;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa(1, "Carlos da Silva", "carlos@gmal.com");
		Pessoa p2 = new Pessoa(2, "Ana Maria", "ana@gmal.com");
		Pessoa p3 = new Pessoa(3, "Osvald Prosper", "osvaldo@gmal.com");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	
	}
	
}
