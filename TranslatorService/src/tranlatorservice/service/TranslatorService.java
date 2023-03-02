package tranlatorservice.service;

import java.util.HashMap;
import java.util.Map;

import translatorservice.impl.ITranslator;

public class TranslatorService implements ITranslator {

	private static final String turkishLocale = "tr_TR";
	private static final String englishLocale = "en_US";
	
	private Map<Integer, String> numberToWordMapTR;
	private Map<String, Integer> wordToNumberMapTR;
	private Map<Integer, String> numberToWordMapEN;
	private Map<String, Integer> wordToNumberMapEN;
	private Map<Integer, String> bigNumberToWordTR;
	private Map<String, Integer> bigWordToNumberTR;
	private Map<Integer, String> bigNumberToWordEN;
	private Map<String, Integer> bigWordToNumberEN;

	public TranslatorService() {
		createHashMap();
	}

	private void createHashMap() {
		// Initialize translation maps for Turkish

		setNumberToWordMapTR(new HashMap<>());
		wordToNumberMapTR = new HashMap<>();
		getNumberToWordMapTR().put(0, "sıfır");
		getNumberToWordMapTR().put(1, "bir");
		getNumberToWordMapTR().put(2, "iki");
		getNumberToWordMapTR().put(3, "üç");
		getNumberToWordMapTR().put(4, "dört");
		getNumberToWordMapTR().put(5, "beş");
		getNumberToWordMapTR().put(6, "altı");
		getNumberToWordMapTR().put(7, "yedi");
		getNumberToWordMapTR().put(8, "sekiz");
		getNumberToWordMapTR().put(9, "dokuz");
		getNumberToWordMapTR().put(10, "on");
		getNumberToWordMapTR().put(20, "yirmi");
		getNumberToWordMapTR().put(30, "otuz");
		getNumberToWordMapTR().put(40, "kırk");
		getNumberToWordMapTR().put(50, "elli");
		getNumberToWordMapTR().put(60, "atmış");
		getNumberToWordMapTR().put(70, "yetmiş");
		getNumberToWordMapTR().put(80, "seksen");
		getNumberToWordMapTR().put(90, "doksan");

		wordToNumberMapTR.put("sıfır", 0);
		wordToNumberMapTR.put("bir", 1);
		wordToNumberMapTR.put("iki", 2);
		wordToNumberMapTR.put("üç", 3);
		wordToNumberMapTR.put("dört", 4);
		wordToNumberMapTR.put("beş", 5);
		wordToNumberMapTR.put("altı", 6);
		wordToNumberMapTR.put("yedi", 7);
		wordToNumberMapTR.put("sekiz", 8);
		wordToNumberMapTR.put("dokuz", 9);
		wordToNumberMapTR.put("on", 10);
		wordToNumberMapTR.put("yirmi", 20);
		wordToNumberMapTR.put("otuz", 30);
		wordToNumberMapTR.put("kırk", 40);
		wordToNumberMapTR.put("elli", 50);
		wordToNumberMapTR.put("atmış", 60);
		wordToNumberMapTR.put("yetmiş", 70);
		wordToNumberMapTR.put("seksen", 80);
		wordToNumberMapTR.put("doksan", 90);

		// Initialize translation maps for English
		numberToWordMapEN = new HashMap<>();
		wordToNumberMapEN = new HashMap<>();
		numberToWordMapEN.put(0, "zero");
		numberToWordMapEN.put(1, "one");
		numberToWordMapEN.put(2, "two");
		numberToWordMapEN.put(3, "three");
		numberToWordMapEN.put(4, "four");
		numberToWordMapEN.put(5, "five");
		numberToWordMapEN.put(6, "six");
		numberToWordMapEN.put(7, "seven");
		numberToWordMapEN.put(8, "eight");
		numberToWordMapEN.put(9, "nine");
		numberToWordMapEN.put(10, "ten");
		numberToWordMapEN.put(11, "eleven");
		numberToWordMapEN.put(12, "twelve");
		numberToWordMapEN.put(13, "thirteen");
		numberToWordMapEN.put(14, "fourteen");
		numberToWordMapEN.put(15, "fifteen");
		numberToWordMapEN.put(16, "sixteen");
		numberToWordMapEN.put(17, "seventeen");
		numberToWordMapEN.put(18, "eighteen");
		numberToWordMapEN.put(19, "nineteen");
		numberToWordMapEN.put(20, "twenty");
		numberToWordMapEN.put(30, "thirty");
		numberToWordMapEN.put(40, "forty");
		numberToWordMapEN.put(50, "fifty");
		numberToWordMapEN.put(60, "sixty");
		numberToWordMapEN.put(70, "seventy");
		numberToWordMapEN.put(80, "eighty");
		numberToWordMapEN.put(90, "ninety");

		wordToNumberMapEN.put("zero", 0);
		wordToNumberMapEN.put("one", 1);
		wordToNumberMapEN.put("two", 2);
		wordToNumberMapEN.put("three", 3);
		wordToNumberMapEN.put("four", 4);
		wordToNumberMapEN.put("five", 5);
		wordToNumberMapEN.put("six", 6);
		wordToNumberMapEN.put("seven", 7);
		wordToNumberMapEN.put("eight", 8);
		wordToNumberMapEN.put("nine", 9);
		wordToNumberMapEN.put("ten", 10);
		wordToNumberMapEN.put("eleven", 11);
		wordToNumberMapEN.put("twelve", 12);
		wordToNumberMapEN.put("thirteen", 13);
		wordToNumberMapEN.put("fourteen", 14);
		wordToNumberMapEN.put("fifteen", 15);
		wordToNumberMapEN.put("sixteen", 16);
		wordToNumberMapEN.put("seventeen", 17);
		wordToNumberMapEN.put("eighteen", 18);
		wordToNumberMapEN.put("nineteen", 19);
		wordToNumberMapEN.put("twenty", 20);
		wordToNumberMapEN.put("thirty", 30);
		wordToNumberMapEN.put("forty", 40);
		wordToNumberMapEN.put("fifty", 50);
		wordToNumberMapEN.put("sixty", 60);
		wordToNumberMapEN.put("seventy", 70);
		wordToNumberMapEN.put("eighty", 80);
		wordToNumberMapEN.put("ninety", 90);

		// Initialize bigger translation maps for Turkish
		setBigNumberToWordTR(new HashMap<>());
		bigWordToNumberTR = new HashMap<>();

		getBigNumberToWordTR().put(100, "yüz");
		getBigNumberToWordTR().put(1000, "bin");
		getBigNumberToWordTR().put(100000, "milyon");
		getBigNumberToWordTR().put(100000000, "milyar");
		bigWordToNumberTR.put("yüz", 100);
		bigWordToNumberTR.put("bin", 1000);
		bigWordToNumberTR.put("milyon", 100000);
		bigWordToNumberTR.put("milyar", 100000000);

		// Initialize bigger translation maps for English
		bigNumberToWordEN = new HashMap<>();
		bigWordToNumberEN = new HashMap<>();

		bigNumberToWordEN.put(100, "hundred");
		bigNumberToWordEN.put(1000, "thousand");
		bigNumberToWordEN.put(1000000, "million");
		bigNumberToWordEN.put(1000000000, "billion");

		bigWordToNumberEN.put("hundred", 100);
		bigWordToNumberEN.put("thousand", 1000);
		bigWordToNumberEN.put("million", 1000000);
		bigWordToNumberEN.put("billion", 1000000000);

	}

	public String numberToWord(int number, String locale) {
		//Converts numbers into words with selected language
		
		Map<Integer, String> numberToWordMap = null;
		if (locale.equals(turkishLocale)) {
			numberToWordMap = getNumberToWordMapTR();
		} else if (locale.equals(englishLocale)) {
			numberToWordMap = numberToWordMapEN;
		} else {
		}

		StringBuilder words = new StringBuilder();

		//Checking HashMap if the number is 0, negative or bigger digit
		if (number == 0) {
			words.append(numberToWordMap.get(number));
		} else if (number < 0) {
			if (locale.equals(turkishLocale)) {
				words.append("eksi ");
			} else {
				words.append("minus ");
			}
			words.append(numberToWord(Math.abs(number), locale));
		} else if ((number / 1000000000) > 0) {
			words.append(numberToWord(number / 1000000000, locale));
			if (locale.equals(turkishLocale)) {
				words.append(" milyar ");
			} else {
				words.append(" billion ");
			}
			if (!isModZero(number, 1000000000)) {
				words.append(numberToWord(number % 1000000000, locale));
			}
		} else if ((number / 1000000) > 0) {
			words.append(numberToWord(number / 1000000, locale));
			if (locale.equals(turkishLocale)) {
				words.append(" milyon ");
			} else {
				words.append(" million ");
			}
			if (!isModZero(number, 1000000)) {
				words.append(numberToWord(number % 1000000, locale));
			}
		} else if ((number / 1000) > 0) {
			words.append(numberToWord(number / 1000, locale));
			if (locale.equals(turkishLocale)) {
				words.append(" bin ");
			} else {
				words.append(" thousand ");
			}
			if (!isModZero(number, 1000)) {
				words.append(numberToWord(number % 1000, locale));
			}
		} else if ((number / 100) > 0) {
			words.append(numberToWord(number / 100, locale));
			if (locale.equals(turkishLocale)) {
				words.append(" yüz ");
			} else {
				words.append(" hundred ");
			}
			if (!isModZero(number, 100)) {
				words.append(numberToWord(number % 100, locale));
			}
		} else if ((number / 10) > 0) {
			if (locale.equals(turkishLocale)) {
				words.append(getNumberToWordMapTR().get((number / 10) * 10));
				words.append(" ");
				if (!isModZero(number, 10)) {
					words.append(numberToWord(number % 10, locale));
				}

			} else {
				if (isNumberBetween(number)) {
					words.append(getNumberToWordMapEN().get(number));
					
				}else {
					words.append(getNumberToWordMapEN().get((number / 10) * 10));
					words.append(" ");
					if (!isModZero(number, 10)) {
						words.append(numberToWord(number % 10, locale));
					}
				}
				
			}
			words.append(" ");
		} else {
			if (locale.equals(turkishLocale)) {
				words.append(getNumberToWordMapTR().get(number));
			} else {
				words.append(numberToWordMapEN.get(number));
			}

		}
		return words.toString();

	}

	@Override
	public double wordToNumber(String words, String locale) {

		Map<String, Integer> wordToNumberMap = null;
		if (locale.equals(turkishLocale)) {
			wordToNumberMap = wordToNumberMapTR;
		} else if (locale.equals(englishLocale)) {
			wordToNumberMap = wordToNumberMapEN;
		} else {
			return 0;
		}

		//Seperates words and puts into Array
		String[] wordArray = words.split(" ");

		double number = 0;
		double temp = 0;
		int negativeNumberCount = 0;

		//Converting String number into integer
		for (String word : wordArray) {
			Integer value = wordToNumberMap.get(word);
			if (value != null) {
				temp += value;
			} else if (word.equals("yüz") || word.equals("hundred")) {
				if (wordArray[0].equals("yüz")) {
					temp = 100;
				} else {
					temp *= 100;
				}
			} else if (word.equals("bin") || word.equals("thousand")) {
				temp *= 1000;
				number += temp;
				temp = 0;
			} else if (word.equals("milyon") || word.equals("million")) {
				temp *= 1000000;
				number += temp;
				temp = 0;
			} else if (word.equals("milyar") || word.equals("billion")) {
				temp *= 1000000000;
				number += temp;
				temp = 0;
			} else if (word.equals("eksi") || word.equals("minus")) {
				// temp *= -1;
				negativeNumberCount = 1;
			} else {
				continue;
			}
		}

		number += temp;

		// if number is negative return with number*-1
		if (negativeNumberCount == 1) {
			return number * (-1);
		}
		return number;

	}

	private boolean isNumberBetween(int number) {
		//checking if the EN number is between 10 and 20 because it has it's own word unlike Turkish (eleven , twelve ...)
		if (10 < number && number < 20) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private boolean isModZero(int number1, int number2) {
		//Checking if the number1 %(mod) number2 is 0
		if (number1 % number2 == 0) {
			return true;
		} else
			return false;

	}

	//Getter and setters for HashMaps
	public Map<Integer, String> getNumberToWordMapTR() {
		return numberToWordMapTR;
	}

	public void setNumberToWordMapTR(Map<Integer, String> numberToWordMapTR) {
		this.numberToWordMapTR = numberToWordMapTR;
	}

	public Map<Integer, String> getNumberToWordMapEN() {
		return numberToWordMapEN;
	}

	public void setNumberToWordMapEN(Map<Integer, String> numberToWordMapEN) {
		this.numberToWordMapEN = numberToWordMapEN;
	}

	public Map<Integer, String> getBigNumberToWordTR() {
		return bigNumberToWordTR;
	}

	public void setBigNumberToWordTR(Map<Integer, String> bigNumberToWordTR) {
		this.bigNumberToWordTR = bigNumberToWordTR;
	}

	public Map<Integer, String> getBigNumberToWordEN() {
		return bigNumberToWordEN;
	}

	public void setBigNumberToWordEN(Map<Integer, String> bigNumberToWordEN) {
		this.bigNumberToWordEN = bigNumberToWordEN;
	}

}
