package com.sirio.api.usuario.model;

import java.util.Collections;
import java.util.List;

public class Calculadora {

	public Integer somaTresMaiores(List<Integer> numeros) {
		int soma = 0;
		
		if (numeros.size() == 0)
			return soma;
		
		if (numeros.size() < 3)
			return numeros.stream().mapToInt(i -> i.intValue()).sum();

		Collections.sort(numeros, Collections.reverseOrder());

		for (int i = 0; i < 3; i++) {
			soma += numeros.get(i);
		}

		return soma;
	}
}
