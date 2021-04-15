package com.hps.luhn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;

public class LuhnUnitTest {

	Luhn luhn;
	Luhn luhnMock;

	@Before
	public void setUp() throws Exception {
		luhn = new Luhn();
		luhnMock = mock(Luhn.class);
	}

	@Test
	public void generateCheckDigit() {
		assertEquals(8, luhn.generateCheckDigit(42424242));
		assertEquals(6, luhn.generateCheckDigit(55555555));
		assertEquals(6, luhn.generateCheckDigit(10569300));
	}

	@Test
	public void isValidLuhn() {
		given(this.luhnMock.isValidLuhn(424242421)).willReturn(false);
		assertFalse(luhnMock.isValidLuhn(424242421));
		given(this.luhnMock.isValidLuhn(424242428)).willReturn(true);
		assertTrue(luhnMock.isValidLuhn(424242428));
	}

	@Test
	public void countRange() {
		assertEquals(1, 927398710, 927398720);
	}

}
