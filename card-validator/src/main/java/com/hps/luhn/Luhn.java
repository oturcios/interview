package com.hps.luhn;

import java.math.BigInteger;

/**
 * @see https://en.wikipedia.org/wiki/Luhn_algorithm#Description
 */
public class Luhn {

	/**
	 * Accepts a card number and determines if the card number is a valid number
	 * with respect to the Luhn algorithm.
	 *
	 * @param cardNumber
	 *            the card number
	 *
	 * @return true if the card number is valid according to the Luhn algorithm,
	 *         false if not
	 */
	public boolean isValidLuhn(String cardNumber) {

		if (isNumeric(cardNumber)) {
			int checkDigit = Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
			return checkDigit == generateCheckDigit(cardNumber.substring(0,cardNumber.length() - 1));
		}

		return false;
	}

	/**
	 * Accepts a partial card number (excluding the last digit) and generates
	 * the appropriate Luhn check digit for the number.
	 *
	 * @param cardNumber
	 *            the card number (not including a check digit)
	 *
	 * @return the check digit
	 */
	public int generateCheckDigit(String cardNumber) {
		boolean doubleDigit = true;
		int sum = 0;

		if (isNumeric(cardNumber)) {
			while (!cardNumber.isEmpty()) {
				// starting from the right (rightmost is the unknown check digit)
				long digit = Long.parseLong(cardNumber.substring(cardNumber.length() - 1));

				if (doubleDigit) { // double the value of every second digit
					digit *= 2;

					// if two digits, use the sum of the digits
					if (digit >= 10) {
						digit = digit / 10 + digit % 10;
					}
				}
				doubleDigit = !doubleDigit;

				sum += digit;

				cardNumber = cardNumber.substring(0,cardNumber.length() - 1); // remaining digits to the left
			}
		}else {
			return -1; // returns -1 to indicate that it is not a valid check digit
		}

		return sum * 9 % 10;
	}

	/**
	 * Accepts two card numbers representing the starting and ending numbers of
	 * a range of card numbers and counts the number of valid Luhn card numbers
	 * that exist in the range, inclusive.
	 *
	 * @param startRange
	 *            the starting card number of the range (may not be valid luhn)
	 * @param endRange
	 *            the ending card number of the range (may not be a valid luhn)
	 *
	 * @return the number of valid Luhn card numbers in the range, inclusive
	 */
	public int countRange(String startRangeStr, String endRangeStr) {

		int count =0;

		if (isNumeric(startRangeStr) && isNumeric(endRangeStr)) {

			BigInteger startRange = new BigInteger(startRangeStr);
			BigInteger endRange = new BigInteger(endRangeStr);

			//verify if the endRange is higher or equal than startRange
			if (endRange.compareTo(startRange) >= 0) {

				while (startRange.compareTo(endRange) <= 0) {
					//Verify if the cardNumber is a valid Luhn card number
					if (isValidLuhn(startRange.toString()))
						count++;

					startRange = startRange.add(new BigInteger("1"));
				}
			}

		}
		return count;
	}

	private boolean isNumeric(String cardNumber) {

		if (cardNumber == null)
			return false;

		String regex = "[0-9]+";
		return cardNumber.matches(regex);
	}
}
