package com.hps.luhn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LuhnUnitTest {

	Luhn luhn;

	@Before
	public void setUp() throws Exception {
		luhn = new Luhn();
	}

	@Test
	public void generateCheckDigit() {
		assertEquals(8, luhn.generateCheckDigit(42424242));
		assertEquals(6, luhn.generateCheckDigit(55555555));
		assertEquals(6, luhn.generateCheckDigit(10569300));
	}

	@Test
	public void isValidLuhn() {
		assertTrue(luhn.isValidLuhn(424242428));
		assertFalse(luhn.isValidLuhn(424242421));
		assertTrue(luhn.isValidLuhn(555555556));
		assertFalse(luhn.isValidLuhn(555555551));
		assertTrue(luhn.isValidLuhn(105693006));
		assertFalse(luhn.isValidLuhn(105693001));
	}

	@Test
	public void countRange() {
		assertEquals(2, luhn.countRange(927398710, 927398730));
		assertEquals(0, luhn.countRange(927398730, 927398710));
	}

}
