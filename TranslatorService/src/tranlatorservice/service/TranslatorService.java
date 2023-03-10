package tranlatorservice.service;

import java.math.BigInteger;
import java.util.Map;

import translatorservice.impl.ITranslator;
import translatorservice.utils.StringToNumberUtil;
import translatorservice.utils.NumberToStringUtil;

public class TranslatorService implements ITranslator {

	@Override
	public String numberToWord(BigInteger number, String locale) {
		/**
		 * Converts a given BigInteger into its word representation, according to the specified locale.
		 * @param number the BigInteger to convert
		 * @param locale the locale to use for word conversion, currently supports "en" (English) and "tr" (Turkish)
		 * @return the word representation of the given BigInteger in the specified locale
		 */
		// Language-specific number word arrays
		String[] ONES = null;
		String[] TEENS = null;
		String[] TENS = null;
		String[] GROUPS = null;

		// Set language-specific number word arrays based on given locale
		switch (locale) {
		case "tr":
			ONES = NumberToStringUtil.ONES_TR;
			TEENS = NumberToStringUtil.TEENS_TR;
			TENS = NumberToStringUtil.TENS_TR;
			GROUPS = NumberToStringUtil.GROUPS_TR;
			break;

		case "en":
			ONES = NumberToStringUtil.ONES_EN;
			TEENS = NumberToStringUtil.TEENS_EN;
			TENS = NumberToStringUtil.TENS_EN;
			GROUPS = NumberToStringUtil.GROUPS_EN;
			break;
		}

		// Initialize word and counting variables
		int negativeCount = 0;
		String words = "";
		int groupIndex = 0;

		// Check if number is 0
		if (number.equals(BigInteger.ZERO)) {
			if (locale.equals("tr")) {
				return "sıfır";
			}
			return "zero";
		}

		// Check if number is negative
		if (number.compareTo(BigInteger.ZERO) < 0) {
			negativeCount++;
		}

		// Convert number to words in groups of 3 digits
		do {
			// divideAndRemainder returns Quotient = groupAndRemainder[0] and remainder = groupAndRemainder[1]
			BigInteger[] groupAndRemainder = number.divideAndRemainder(BigInteger.valueOf(1000));
			BigInteger group = groupAndRemainder[1];
			number = groupAndRemainder[0];

			// Convert group of 3 digits to words and append to total word string
			if (!group.equals(BigInteger.ZERO)) {
				String groupWords = convertGroupToString(group.abs(), ONES, TEENS, TENS, locale);
				words = groupWords + GROUPS[groupIndex] + " " + words;
			}

			groupIndex++;
		} while (!number.equals(BigInteger.ZERO));

		// Append "minus" or "eksi" to word string if number was negative
		if (negativeCount > 0) {
			
			words = (locale.equals("tr") ? "eksi " : "minus ") + words ;
		}

		return words.trim();
	}
	

	@Override
	public BigInteger wordToNumber(String word, String locale) {
		/**

		Converts a given word in the specified locale to a BigInteger representation of the number.
		@param word The word to be converted.
		@param locale The locale of the word to be converted.
		@return The BigInteger representation of the number.
		*/
		
		// Get the maps for the locale being used.
		Map<String, BigInteger> CURRENT_ONES = null;
		Map<String, BigInteger> CURRENT_TEENS = null;
		Map<String, BigInteger> CURRENT_TENS = null;
		Map<String, BigInteger> CURRENT_GROUPS = null;

		switch (locale) {
		case "tr":
			CURRENT_ONES = StringToNumberUtil.ONES_PLACE_TR;
			CURRENT_TEENS = StringToNumberUtil.TEENS_PLACE_TR;
			CURRENT_TENS = StringToNumberUtil.TENS_PLACE_TR;
			CURRENT_GROUPS = StringToNumberUtil.GROUPS_PLACE_TR;
			break;
		case "en":
			CURRENT_ONES = StringToNumberUtil.ONES_PLACE_EN;
			CURRENT_TEENS = StringToNumberUtil.TEENS_PLACE_EN;
			CURRENT_TENS = StringToNumberUtil.TENS_PLACE_EN;
			CURRENT_GROUPS = StringToNumberUtil.GROUPS_PLACE_EN;
			break;
		}

		// Initialize variables
		int negativeCounter = 0;
		String[] words = word.toLowerCase().split(" ");
		if (words[0].equals("eksi") || words[0].equals("minus")) {
			negativeCounter++;
			words[0] = "";
		}

		BigInteger number = BigInteger.ZERO;
		BigInteger groupValue = BigInteger.ONE;
		// Iterate through the words array and convert each word to a BigInteger value
		for (int i = words.length - 1; i >= 0; i--) {
			BigInteger value = null;
			switch (words[i]) {
			// Handle ones and teens places
			case "one":
			case "bir":			
			case "two":
			case "iki":			
			case "three":
			case "üç":			
			case "four":
			case "dört":			
			case "five":
			case "beş":		
			case "six":
			case "altı":	
			case "seven":
			case "yedi":	
			case "eight":
			case "sekiz":	
			case "nine":
			case "dokuz":
				value = CURRENT_ONES.get(words[i]);
				break;
			case "eleven":	
			case "twelve":	
			case "thirteen":
			case "fourteen":
			case "fifteen":
			case "sixteen":
			case "seventeen":
			case "eighteen":
			case "nineteen":
				value = CURRENT_TEENS.get(words[i]);
				break;
			case "ten":
			case "on":
			case "twenty":
			case "yirmi":
			case "thirty":
			case "otuz":
			case "forty":
			case "kırk":
			case "fifty":
			case "elli":
			case "sixty":
			case "atmış":
			case "seventy":
			case "yetmiş":
			case "eighty":
			case "seksen":
			case "ninety":
			case "doksan":
				value = CURRENT_TENS.get(words[i]);
				break;
				// Handle group values
			default:
				if (CURRENT_GROUPS.containsKey(words[i])) {
					BigInteger newValue = CURRENT_GROUPS.get(words[i]);

					if (newValue.compareTo(groupValue) < 0) {
						groupValue = groupValue.multiply(newValue);
						number = number.add(groupValue.subtract(BigInteger.ONE));
					} else {
						groupValue = newValue;
					}
				}
			}

			if (value != null) {
				number = number.add(
						value.compareTo(BigInteger.TEN) < 0 ? groupValue.multiply(value) : value.multiply(groupValue));
			}
		}
		if (negativeCounter > 0) {
			number = number.multiply(BigInteger.valueOf(-1));
		}

		return number;
	}
	

	private static String convertGroupToString(BigInteger number, String[] ONES, String[] TEENS, String[] TENS, String locale) {
		/**
		 * Converts a given BigInteger number into a string representation using the provided arrays of words for ones, teens, and tens.
		 * @param number The BigInteger number to convert.
		 * @param ONES An array of words for ones, e.g. ["zero", "one", "two", ...].
		 * @param TEENS An array of words for teens, e.g. ["ten", "eleven", "twelve", ...].
		 * @param TENS An array of words for tens, e.g. ["twenty", "thirty", "forty", ...].
		 * @param locale The locale to use for language-specific formatting.
		 * @return A string representation of the given number.
		 */
		
		// Initialize an empty string to hold the words that represent the given number.
		String words = "";
		// If the number is greater than or equal to 100, add the corresponding word for the hundreds place to the string.
		if (number.compareTo(BigInteger.valueOf(100)) >= 0) {
			words = ONES[number.divide(BigInteger.valueOf(100)).intValue()];
			words += locale.equals("en") ? " hundred " : " yüz ";
			number = number.mod(BigInteger.valueOf(100));
		}
		// If the number is 10, add the corresponding word for 10 and return the resulting string.
		if (number.compareTo(BigInteger.TEN) == 0) {
			words += TENS[1] + " ";
			return words;
		}
		// If the number is between 10 and 19, add the corresponding word for the teens place and return the resulting string.
		if (number.compareTo(BigInteger.TEN) > 0 && number.compareTo(BigInteger.valueOf(19)) <= 0) {
			words += TEENS[number.mod(BigInteger.TEN).intValue()] + " ";
			return words;
		} else if (number.compareTo(BigInteger.TEN) == 0 || number.compareTo(BigInteger.valueOf(20)) >= 0) {
			words += TENS[number.divide(BigInteger.TEN).intValue()] + " ";
			number = number.mod(BigInteger.TEN);
		}

		// If the number is between 1 and 9, add the corresponding word for the ones place to the string.
		if (number.compareTo(BigInteger.ONE) >= 0 && number.compareTo(BigInteger.valueOf(9)) <= 0) {
			words += ONES[number.intValue()] + " ";
		}

		return words;
	}

	public boolean validateNumber(String word, String locale) {
		/**

		Determines if the input string represents a valid number in the given locale.

		@param word the input string to validate

		@param locale the locale to use for validation

		@return true if the input string is a valid number in the given locale, false otherwise
		*/

		// Set up maps for the current locale's number words
		Map<String, BigInteger> CURRENT_ONES = null;
		Map<String, BigInteger> CURRENT_TEENS = null;
		Map<String, BigInteger> CURRENT_TENS = null;
		Map<String, BigInteger> CURRENT_GROUPS = null;
		
		// Select the appropriate maps based on the input locale
		switch (locale) {
		case "tr":
			CURRENT_ONES = StringToNumberUtil.ONES_PLACE_TR;
			CURRENT_TEENS = StringToNumberUtil.TEENS_PLACE_TR;
			CURRENT_TENS = StringToNumberUtil.TENS_PLACE_TR;
			CURRENT_GROUPS = StringToNumberUtil.GROUPS_PLACE_TR;
			break;
		case "en":
			CURRENT_ONES = StringToNumberUtil.ONES_PLACE_EN;
			CURRENT_TEENS = StringToNumberUtil.TEENS_PLACE_EN;
			CURRENT_TENS = StringToNumberUtil.TENS_PLACE_EN;
			CURRENT_GROUPS = StringToNumberUtil.GROUPS_PLACE_EN;
			break;
		}

		// Check if the input string matches any of the number words for the current locale
		if (CURRENT_ONES.keySet().contains(word) || CURRENT_TEENS.keySet().contains(word)
				|| CURRENT_TENS.keySet().contains(word) || CURRENT_GROUPS.keySet().contains(word)) {

			return true;
		}

		return false;

	}

}
