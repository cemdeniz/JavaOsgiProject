package tranlatorservice.service;

import java.math.BigInteger;
import java.util.Map;

import translatorservice.impl.ITranslator;
import translatorservice.utils.StringToNumberUtil;
import translatorservice.utils.NumberToStringUtil;

public class TranslatorService implements ITranslator {

	@Override
	public String numberToWord(BigInteger number, String locale) {

		String[] ONES = null;
		String[] TEENS = null;
		String[] TENS = null;
		String[] GROUPS = null;

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

		int negativeCount = 0;
		String words = "";
		int groupIndex = 0;

		if (number.equals(BigInteger.ZERO)) {
			if (locale.equals("tr")) {
				return "sıfır";
			}
			return "zero";
		}

		if (number.compareTo(BigInteger.ZERO) < 0) {
			negativeCount++;
		}

		do {
			// divideAndRemainder returns Quotient = groupAndRemainder[0] and remainder =
			// groupAndRemainder[1]
			BigInteger[] groupAndRemainder = number.divideAndRemainder(BigInteger.valueOf(1000));
			//remainder value
			BigInteger group = groupAndRemainder[1];
			//Quotient value
			number = groupAndRemainder[0];

			if (!group.equals(BigInteger.ZERO)) {
				String groupWords = convertGroupToString(group.abs(), ONES, TEENS, TENS, locale);
				words = groupWords + GROUPS[groupIndex] + " " + words;
			}

			groupIndex++;
		} while (!number.equals(BigInteger.ZERO));

		if (negativeCount > 0) {
			
			words = (locale.equals("tr") ? "eksi " : "minus ") + words ;
		}

		return words.trim();
	}
	

	@Override
	public BigInteger wordToNumber(String word, String locale) {

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

		int negativeCounter = 0;
		String[] words = word.toLowerCase().split(" ");
		if (words[0].equals("eksi") || words[0].equals("minus")) {
			negativeCounter++;
			words[0] = "";
		}

		BigInteger number = BigInteger.ZERO;
		BigInteger groupValue = BigInteger.ONE;
		for (int i = words.length - 1; i >= 0; i--) {
			BigInteger value = null;
			switch (words[i]) {
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
		// This method called after we seperate our number into smaller portion to execute specific code
		String words = "";
		// Checking if the number is bigger than 100
		if (number.compareTo(BigInteger.valueOf(100)) >= 0) {
			words = ONES[number.divide(BigInteger.valueOf(100)).intValue()];
			words += locale.equals("en") ? " hundred " : " yüz ";
			number = number.mod(BigInteger.valueOf(100));
		}
		// Checking if the number is 10
		if (number.compareTo(BigInteger.TEN) == 0) {
			words += TENS[1] + " ";
			return words;
		}
		// Checking if the number is between 10 and 19
		if (number.compareTo(BigInteger.TEN) > 0 && number.compareTo(BigInteger.valueOf(19)) <= 0) {
			words += TEENS[number.mod(BigInteger.TEN).intValue()] + " ";
			return words;
		} else if (number.compareTo(BigInteger.TEN) == 0 || number.compareTo(BigInteger.valueOf(20)) >= 0) {
			words += TENS[number.divide(BigInteger.TEN).intValue()] + " ";
			number = number.mod(BigInteger.TEN);
		}

		// Checking if the number is between 1 and 9
		if (number.compareTo(BigInteger.ONE) >= 0 && number.compareTo(BigInteger.valueOf(9)) <= 0) {
			words += ONES[number.intValue()] + " ";
		}

		return words;
	}

	public boolean validateNumber(String word, String locale) {

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

		if (CURRENT_ONES.keySet().contains(word) || CURRENT_TEENS.keySet().contains(word)
				|| CURRENT_TENS.keySet().contains(word) || CURRENT_GROUPS.keySet().contains(word)) {

			return true;
		}

		return false;

	}

}
