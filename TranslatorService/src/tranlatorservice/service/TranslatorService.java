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
		 * Converts a given BigInteger into its word representation, according to the
		 * specified locale.
		 * 
		 * @param number the BigInteger to convert
		 * @param locale the locale to use for word conversion, currently supports "en"
		 *               (English) and "tr" (Turkish)
		 * @return the word representation of the given BigInteger in the specified
		 *         locale
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
			// divideAndRemainder returns Quotient = groupAndRemainder[0] and remainder =
			// groupAndRemainder[1]
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

			words = (locale.equals("tr") ? "eksi " : "minus ") + words;
		}

		return words.trim();
	}

	@Override
	public BigInteger wordToNumber(String word, String locale) {
		/**
		 * 
		 * Converts a given word in the specified locale to a BigInteger representation
		 * of the number.
		 * 
		 * @param word   The word to be converted.
		 * @param locale The locale of the word to be converted.
		 * @return The BigInteger representation of the number.
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
			String flag = "";

			// Searching the word in our Hash Maps for ONES, TEENS and TENS
			for (Map.Entry<String, BigInteger> searchWord : CURRENT_ONES.entrySet()) {
				if (words[i].equals(searchWord.getKey())) {
					flag = "ones"; // number is between 0-10
					
				}
			}
			for (Map.Entry<String, BigInteger> searchWord : CURRENT_TEENS.entrySet()) {
				if (words[i].equals(searchWord.getKey())) {
					flag = "teens"; // number is between 10-20
				}
			}
			for (Map.Entry<String, BigInteger> searchWord : CURRENT_TENS.entrySet()) {
				if (words[i].equals(searchWord.getKey())) {
					flag = "tens"; // number is between 20-100
				}
			}

			switch (flag) {
			// Handle numbers that smaller than 1000
			case "ones":
				value = CURRENT_ONES.get(words[i]);
				break;
			case "teens":
				value = CURRENT_TEENS.get(words[i]);
				break;
			case "tens":
				value = CURRENT_TENS.get(words[i]);
				break;
			// Handle group values
			default:
				if (CURRENT_GROUPS.containsKey(words[i])) {
					//adding one before "hundred" and "thousand" if it's the first word.(Ex: hundred five => one hundred five)
					if(i==0) {
						if (words[i].equals("yüz") || words[i].equals("hundred") || words[i].equals("bin") || words[i].equals("thousand")) {
							value= BigInteger.ONE;
						}
					}
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

	private static String convertGroupToString(BigInteger number, String[] ONES, String[] TEENS, String[] TENS,
			String locale) {
		/**
		 * Converts a given BigInteger number into a string representation using the
		 * provided arrays of words for ones, teens, and tens.
		 * 
		 * @param number The BigInteger number to convert.
		 * @param ONES   An array of words for ones, e.g. ["zero", "one", "two", ...].
		 * @param TEENS  An array of words for teens, e.g. ["ten", "eleven", "twelve",
		 *               ...].
		 * @param TENS   An array of words for tens, e.g. ["twenty", "thirty", "forty",
		 *               ...].
		 * @param locale The locale to use for language-specific formatting.
		 * @return A string representation of the given number.
		 */

		// Initialize an empty string to hold the words that represent the given number.
		String words = "";
		// If the number is greater than or equal to 100, add the corresponding word for
		// the hundreds place to the string.
		if (number.compareTo(BigInteger.valueOf(100)) >= 0) {
			words = ONES[number.divide(BigInteger.valueOf(100)).intValue()];
			words += locale.equals("en") ? " hundred " : " yüz ";
			number = number.mod(BigInteger.valueOf(100));
		}
		// If the number is 10, add the corresponding word for 10 and return the
		// resulting string.
		if (number.compareTo(BigInteger.TEN) == 0) {
			words += TENS[1] + " ";
			return words;
		}
		// If the number is between 10 and 19, add the corresponding word for the teens
		// place and return the resulting string.
		if (number.compareTo(BigInteger.TEN) > 0 && number.compareTo(BigInteger.valueOf(19)) <= 0) {
			words += TEENS[number.mod(BigInteger.TEN).intValue()] + " ";
			return words;
		} else if (number.compareTo(BigInteger.TEN) == 0 || number.compareTo(BigInteger.valueOf(20)) >= 0) {
			words += TENS[number.divide(BigInteger.TEN).intValue()] + " ";
			number = number.mod(BigInteger.TEN);
		}

		// If the number is between 1 and 9, add the corresponding word for the ones
		// place to the string.
		if (number.compareTo(BigInteger.ONE) >= 0 && number.compareTo(BigInteger.valueOf(9)) <= 0) {
			words += ONES[number.intValue()] + " ";
		}

		return words;
	}

	public boolean validateNumber(String word, String locale) {
		/**
		 * 
		 * Determines if the input string represents a valid number in the given locale.
		 * 
		 * @param word   the input string to validate
		 * 
		 * @param locale the locale to use for validation
		 * 
		 * @return true if the input string is a valid number in the given locale, false
		 *         otherwise
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

		// Check if the input string matches any of the number words for the current
		// locale
		if (CURRENT_ONES.keySet().contains(word) || CURRENT_TEENS.keySet().contains(word)
				|| CURRENT_TENS.keySet().contains(word) || CURRENT_GROUPS.keySet().contains(word)) {

			return true;
		}

		return false;

	}

	public boolean validateEdgeCases(String number, String locale) {
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

		boolean hasRepeatedWords = checkRepeatedWords(number, ONES, TENS, TEENS, GROUPS);

		return hasRepeatedWords;

	}

	private static boolean checkRepeatedWords(String input, String[] ONES, String[] TENS, String[] TEENS,
			String[] GROUPS) {
		String arrayName = "";
		String[] words = input.toLowerCase().split(" ");
		for (int i = words.length - 1; i >= 0; i--) {

			// identifying our word, "hundred" or "yüz" is special case
			arrayName = (words[i].equals("hundred")) ? "hundred" : "";

			if (arrayName.isEmpty()) {
				arrayName = words[i].equals("yüz") ? "yüz" : "";
			}

			if (arrayName.isEmpty()) {
				if (isInArray(words[i], ONES)) {
					arrayName = "ones";
				}
			}
			if (arrayName.isEmpty()) {
				if (isInArray(words[i], TEENS)) {
					arrayName = "teens";
				}
			}
			if (arrayName.isEmpty()) {
				if (isInArray(words[i], TENS)) {
					arrayName = "tens";
				}
			}
			if (arrayName.isEmpty()) {
				if (isInArray(words[i], GROUPS)) {
					arrayName = "groups";
				}
			}

			// Looking to previous number if it's valid.(Ex: can't write ones or teens
			// before ones. "two fifteen", is not valid)
			switch (arrayName) {
			case "ones":
				if (words.length > 1 && i != 0) {

					if (isInArray(words[i - 1], ONES) || isInArray(words[i - 1], TEENS)) {
						return false;
					}
				}
				break;
			case "teens":
				if (words.length > 1 && i != 0) {
					if (isInArray(words[i - 1], ONES) || isInArray(words[i - 1], TEENS)
							|| isInArray(words[i - 1], TENS)) {
						return false;
					}
				}
				break;
			case "tens":
				if (words.length > 1 && i != 0) {
					if (isInArray(words[i - 1], ONES) || isInArray(words[i - 1], TEENS)
							|| isInArray(words[i - 1], TENS)) {
						return false;
					}
				}
				break;
			case "groups":
				if (words.length > 1 && i != 0) {
					if (isInArray(words[i - 1], GROUPS)) {
						return false;
					}
					int indexI = whichIndex(words[i], GROUPS);
					for (int j = i - 2; j > 0; j--) {
						if (isInArray(words[j], GROUPS)) {
							int indexJ = whichIndex(words[j], GROUPS);
							if (indexI >= indexJ) {
								return false;
							}
						}
					}
				}
				break;
			// "hundred" and "yüz" are special cases.
			case "hundred":
				if (words.length > 1 && i != 0) {
					if (isInArray(words[i - 1], TEENS) || isInArray(words[i - 1], TENS)
							|| (words[i - 1].equals("hundred"))) {
						return false;
					}

				}
				break;
			case "yüz":
				if (words.length > 1 && i != 0) {
					if (isInArray(words[i - 1], TENS) || (words[i - 1].equals("yüz"))) {
						return false;
					}
					
					if(i>1 && isInArray(words[i - 1], ONES)) {
						if(isInArray(words[i-2], TENS) || (words[i - 2].equals("yüz"))) {
							return false;
						}
					}

				}
				break;

			}

		}
		return true;

	}

	private static boolean isInArray(String word, String[] Array) {
		for (String input : Array) {
			if (word.equals(input)) {
				return true;
			}
		}
		return false;
	}

	private static int whichIndex(String word, String[] Array) {
		int index = -1;
		for (int i = 0; i < Array.length; i++) {
			if (Array[i].equals(word)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
