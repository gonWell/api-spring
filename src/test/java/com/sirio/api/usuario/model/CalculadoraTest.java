package com.sirio.api.usuario.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class CalculadoraTest {

	@InjectMocks
	Calculadora calculadora;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void soma22() {

		List<Integer> numeros = new ArrayList();

		numeros.add(10);
		numeros.add(4);
		numeros.add(3);
		numeros.add(2);
		numeros.add(5);
		numeros.add(7);

		int result = calculadora.somaTresMaiores(numeros);

		assertEquals(22, result);
	}

	@Test
	void soma3() {
		List<Integer> numeros = new ArrayList();

		numeros.add(2);
		numeros.add(1);

		int result = calculadora.somaTresMaiores(numeros);

		assertEquals(3, result);
	}
	
	@Test
	void soma0() {
		List<Integer> numeros = new ArrayList();
		int result = calculadora.somaTresMaiores(numeros);

		assertEquals(0, result);
	}

}
