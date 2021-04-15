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
		assertEquals(-1, luhn.generateCheckDigit("not a number"));
		assertEquals(-1, luhn.generateCheckDigit(null));
		assertEquals(8, luhn.generateCheckDigit("42424242"));
		assertEquals(6, luhn.generateCheckDigit("55555555"));
		assertEquals(6, luhn.generateCheckDigit("10569300"));
		assertEquals(2, luhn.generateCheckDigit("424242424242424"));
		assertEquals(6, luhn.generateCheckDigit("400005665566555"));
		assertEquals(1, luhn.generateCheckDigit("37144963539843"));
	}

	@Test
	public void isValidLuhn() {
		assertFalse(luhn.isValidLuhn("not a number"));
		assertFalse(luhn.isValidLuhn(null));
		assertTrue(luhn.isValidLuhn("424242428"));
		assertFalse(luhn.isValidLuhn("424242421"));
		assertTrue(luhn.isValidLuhn("555555556"));
		assertFalse(luhn.isValidLuhn("555555551"));
		assertTrue(luhn.isValidLuhn("105693006"));
		assertFalse(luhn.isValidLuhn("105693001"));
		assertFalse(luhn.isValidLuhn("4242424242424241"));
		assertTrue(luhn.isValidLuhn("4242424242424242"));
		assertFalse(luhn.isValidLuhn("4000056655665551"));
		assertTrue(luhn.isValidLuhn("4000056655665556"));
		assertFalse(luhn.isValidLuhn("371449635398432"));
		assertTrue(luhn.isValidLuhn("371449635398431"));
	}

	@Test
	public void countRange() {
		assertEquals(2, luhn.countRange("927398710", "927398730"));
		assertEquals(0, luhn.countRange("927398730", "927398710"));
		assertEquals(6, luhn.countRange("4242424242424241", "4242424242424291"));
		assertEquals(5, luhn.countRange("42424242424242414242424242424241", "42424242424242414242424242424291"));
		assertEquals(0, luhn.countRange("not a number", "1"));
		assertEquals(0, luhn.countRange("1", "not a number"));
		assertEquals(0, luhn.countRange("not a number", "not a number either"));
		assertEquals(0, luhn.countRange("not a number", null));
		assertEquals(0, luhn.countRange(null, "not a number"));
		assertEquals(0, luhn.countRange(null, "1"));
		assertEquals(0, luhn.countRange("1", null));
		assertEquals(0, luhn.countRange(null, null));
	}

}
