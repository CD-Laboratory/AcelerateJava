package com.programar.cursoop.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	//decodificar um parametro recebido
	public static String decodeParam(String s) {

		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	//converter a URL recebida em valores inteiros
	public static List<Integer> decodeIntList(String s){
		
		String[] vet = s.split(",");
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		
		/* ou, você pode fazer usando lambda
		 * 
		 * return Arrays.asList(s.split(",")).stream().map(x ->
		 * Integer.parseInt(x)).collect(Collectors.toList());
		 */
	}
}
