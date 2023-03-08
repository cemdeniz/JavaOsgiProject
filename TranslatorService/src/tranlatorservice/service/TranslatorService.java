package tranlatorservice.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import translatorservice.impl.ITranslator;

public class TranslatorService implements ITranslator {

	private static final String turkishLocale = "tr_TR";
	private static final String englishLocale = "en_US";

	private Map<BigInteger, String> numberToWordMapTR;
	private Map<String, BigInteger> wordToNumberMapTR;
	private Map<BigInteger, String> bigNumberToWordTR;
	private Map<String, BigInteger> bigWordToNumberTR;

	private Map<BigInteger, String> numberToWordMapEN;
	private Map<String, BigInteger> wordToNumberMapEN;
	private Map<BigInteger, String> bigNumberToWordEN;
	private Map<String, BigInteger> bigWordToNumberEN;
	
	private static final String decillion = "1000000000000000000000000000000000";
	private static final String nonillion = "1000000000000000000000000000000";
	private static final String octillion = "1000000000000000000000000000";
	private static final String septillion = "1000000000000000000000000";
	private static final String sextillion = "1000000000000000000000";

	public TranslatorService() {
		createHashMap();
	}

	private void createHashMap() {
		// Initialize translation maps for Turkish

		setNumberToWordMapTR(new HashMap<>());
		wordToNumberMapTR = new HashMap<>();

		getNumberToWordMapTR().put(BigInteger.ZERO, "sıfır");
		getNumberToWordMapTR().put(BigInteger.ONE, "bir");
		getNumberToWordMapTR().put(BigInteger.valueOf(2), "iki");
		getNumberToWordMapTR().put(BigInteger.valueOf(3), "üç");
		getNumberToWordMapTR().put(BigInteger.valueOf(4), "dört");
		getNumberToWordMapTR().put(BigInteger.valueOf(5), "beş");
		getNumberToWordMapTR().put(BigInteger.valueOf(6), "altı");
		getNumberToWordMapTR().put(BigInteger.valueOf(7), "yedi");
		getNumberToWordMapTR().put(BigInteger.valueOf(8), "sekiz");
		getNumberToWordMapTR().put(BigInteger.valueOf(9), "dokuz");
		getNumberToWordMapTR().put(BigInteger.valueOf(10), "on");
		getNumberToWordMapTR().put(BigInteger.valueOf(20), "yirmi");
		getNumberToWordMapTR().put(BigInteger.valueOf(30), "otuz");
		getNumberToWordMapTR().put(BigInteger.valueOf(40), "kırk");
		getNumberToWordMapTR().put(BigInteger.valueOf(50), "elli");
		getNumberToWordMapTR().put(BigInteger.valueOf(60), "atmış");
		getNumberToWordMapTR().put(BigInteger.valueOf(70), "yetmiş");
		getNumberToWordMapTR().put(BigInteger.valueOf(80), "seksen");
		getNumberToWordMapTR().put(BigInteger.valueOf(90), "doksan");

		wordToNumberMapTR.put("sıfır", BigInteger.ZERO);
		wordToNumberMapTR.put("bir", BigInteger.ONE);
		wordToNumberMapTR.put("iki", BigInteger.valueOf(2));
		wordToNumberMapTR.put("üç", BigInteger.valueOf(3));
		wordToNumberMapTR.put("dört", BigInteger.valueOf(4));
		wordToNumberMapTR.put("beş", BigInteger.valueOf(5));
		wordToNumberMapTR.put("altı", BigInteger.valueOf(6));
		wordToNumberMapTR.put("yedi", BigInteger.valueOf(7));
		wordToNumberMapTR.put("sekiz", BigInteger.valueOf(8));
		wordToNumberMapTR.put("dokuz", BigInteger.valueOf(9));
		wordToNumberMapTR.put("on", BigInteger.valueOf(10));
		wordToNumberMapTR.put("yirmi", BigInteger.valueOf(20));
		wordToNumberMapTR.put("otuz", BigInteger.valueOf(30));
		wordToNumberMapTR.put("kırk", BigInteger.valueOf(40));
		wordToNumberMapTR.put("elli", BigInteger.valueOf(50));
		wordToNumberMapTR.put("atmış", BigInteger.valueOf(60));
		wordToNumberMapTR.put("yetmiş", BigInteger.valueOf(70));
		wordToNumberMapTR.put("seksen", BigInteger.valueOf(80));
		wordToNumberMapTR.put("doksan", BigInteger.valueOf(90));

		// Initialize translation maps for English
		numberToWordMapEN = new HashMap<>();
		wordToNumberMapEN = new HashMap<>();

		numberToWordMapEN.put(BigInteger.ZERO, "zero");
		numberToWordMapEN.put(BigInteger.ONE, "one");
		numberToWordMapEN.put(BigInteger.valueOf(2), "two");
		numberToWordMapEN.put(BigInteger.valueOf(3), "three");
		numberToWordMapEN.put(BigInteger.valueOf(4), "four");
		numberToWordMapEN.put(BigInteger.valueOf(5), "five");
		numberToWordMapEN.put(BigInteger.valueOf(6), "six");
		numberToWordMapEN.put(BigInteger.valueOf(7), "seven");
		numberToWordMapEN.put(BigInteger.valueOf(8), "eight");
		numberToWordMapEN.put(BigInteger.valueOf(9), "nine");
		numberToWordMapEN.put(BigInteger.valueOf(10), "ten");
		numberToWordMapEN.put(BigInteger.valueOf(11), "eleven");
		numberToWordMapEN.put(BigInteger.valueOf(12), "twelve");
		numberToWordMapEN.put(BigInteger.valueOf(13), "thirteen");
		numberToWordMapEN.put(BigInteger.valueOf(14), "fourteen");
		numberToWordMapEN.put(BigInteger.valueOf(15), "fifteen");
		numberToWordMapEN.put(BigInteger.valueOf(16), "sixteen");
		numberToWordMapEN.put(BigInteger.valueOf(17), "seventeen");
		numberToWordMapEN.put(BigInteger.valueOf(18), "eighteen");
		numberToWordMapEN.put(BigInteger.valueOf(19), "nineteen");
		numberToWordMapEN.put(BigInteger.valueOf(20), "twenty");
		numberToWordMapEN.put(BigInteger.valueOf(30), "thirty");
		numberToWordMapEN.put(BigInteger.valueOf(40), "forty");
		numberToWordMapEN.put(BigInteger.valueOf(50), "fifty");
		numberToWordMapEN.put(BigInteger.valueOf(60), "sixty");
		numberToWordMapEN.put(BigInteger.valueOf(70), "seventy");
		numberToWordMapEN.put(BigInteger.valueOf(80), "eighty");
		numberToWordMapEN.put(BigInteger.valueOf(90), "ninety");

		wordToNumberMapEN.put("zero", BigInteger.ZERO);
		wordToNumberMapEN.put("one", BigInteger.ONE);
		wordToNumberMapEN.put("two", BigInteger.valueOf(2));
		wordToNumberMapEN.put("three", BigInteger.valueOf(3));
		wordToNumberMapEN.put("four", BigInteger.valueOf(4));
		wordToNumberMapEN.put("five", BigInteger.valueOf(5));
		wordToNumberMapEN.put("six", BigInteger.valueOf(6));
		wordToNumberMapEN.put("seven", BigInteger.valueOf(7));
		wordToNumberMapEN.put("eight", BigInteger.valueOf(8));
		wordToNumberMapEN.put("nine", BigInteger.valueOf(9));
		wordToNumberMapEN.put("ten", BigInteger.valueOf(10));
		wordToNumberMapEN.put("eleven", BigInteger.valueOf(11));
		wordToNumberMapEN.put("twelve", BigInteger.valueOf(12));
		wordToNumberMapEN.put("thirteen", BigInteger.valueOf(13));
		wordToNumberMapEN.put("fourteen", BigInteger.valueOf(14));
		wordToNumberMapEN.put("fifteen", BigInteger.valueOf(15));
		wordToNumberMapEN.put("sixteen", BigInteger.valueOf(16));
		wordToNumberMapEN.put("seventeen", BigInteger.valueOf(17));
		wordToNumberMapEN.put("eighteen", BigInteger.valueOf(18));
		wordToNumberMapEN.put("nineteen", BigInteger.valueOf(19));
		wordToNumberMapEN.put("twenty", BigInteger.valueOf(20));
		wordToNumberMapEN.put("thirty", BigInteger.valueOf(30));
		wordToNumberMapEN.put("forty", BigInteger.valueOf(40));
		wordToNumberMapEN.put("fifty", BigInteger.valueOf(50));
		wordToNumberMapEN.put("sixty", BigInteger.valueOf(60));
		wordToNumberMapEN.put("seventy", BigInteger.valueOf(70));
		wordToNumberMapEN.put("eighty", BigInteger.valueOf(80));
		wordToNumberMapEN.put("ninety", BigInteger.valueOf(90));

		// Initialize bigger translation maps for Turkish
		setBigNumberToWordTR(new HashMap<>());
		bigWordToNumberTR = new HashMap<>();

		getBigNumberToWordTR().put(BigInteger.valueOf(100), "yüz");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000), "bin");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000000), "milyon");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000000000), "milyar");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000000000000L), "trilyon");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000000000000000L), "katrilyon");
		getBigNumberToWordTR().put(BigInteger.valueOf(1000000000000000000L), "kentrilyon");
		getBigNumberToWordTR().put(new BigInteger(sextillion), "sekstilyon");
		getBigNumberToWordTR().put(new BigInteger(septillion), "septilyon");
		getBigNumberToWordTR().put(new BigInteger(octillion), "oktilyon");
		getBigNumberToWordTR().put(new BigInteger(nonillion), "nonilyon");
		getBigNumberToWordTR().put(new BigInteger(decillion), "desilyon");

		bigWordToNumberTR.put("yüz", BigInteger.valueOf(100));
		bigWordToNumberTR.put("bin", BigInteger.valueOf(1000));
		bigWordToNumberTR.put("milyon", BigInteger.valueOf(1000000));
		bigWordToNumberTR.put("milyar", BigInteger.valueOf(1000000000));
		bigWordToNumberTR.put("trilyon", BigInteger.valueOf(1000000000000L));
		bigWordToNumberTR.put("katrilyon", BigInteger.valueOf(1000000000000000L));
		bigWordToNumberTR.put("kentrilyon", BigInteger.valueOf(1000000000000000000L));
		bigWordToNumberTR.put("sekstilyon", new BigInteger(sextillion));
		bigWordToNumberTR.put("septilyon", new BigInteger(septillion));
		bigWordToNumberTR.put("oktilyon", new BigInteger(octillion));
		bigWordToNumberTR.put("nonilyon", new BigInteger(nonillion));
		bigWordToNumberTR.put("desilyon", new BigInteger(decillion));

		// Initialize bigger translation maps for English
		bigNumberToWordEN = new HashMap<>();
		bigWordToNumberEN = new HashMap<>();

		bigNumberToWordEN.put(BigInteger.valueOf(100), "hundred");
		bigNumberToWordEN.put(BigInteger.valueOf(1000), "thousand");
		bigNumberToWordEN.put(BigInteger.valueOf(1000000), "million");
		bigNumberToWordEN.put(BigInteger.valueOf(1000000000), "billion");
		bigNumberToWordEN.put(BigInteger.valueOf(1000000000000L), "trillion");
		bigNumberToWordEN.put(BigInteger.valueOf(1000000000000000L), "quadrillion");
		bigNumberToWordEN.put(BigInteger.valueOf(1000000000000000000L), "quintillion");
		bigNumberToWordEN.put(new BigInteger(sextillion), "sextillion");
		bigNumberToWordEN.put(new BigInteger(septillion), "septillion");
		bigNumberToWordEN.put(new BigInteger(octillion), "octillion");
		bigNumberToWordEN.put(new BigInteger(nonillion), "nonillion");
		bigNumberToWordEN.put(new BigInteger(decillion), "decillion");

		bigWordToNumberEN.put("hundred", BigInteger.valueOf(100));
		bigWordToNumberEN.put("thousand", BigInteger.valueOf(1000));
		bigWordToNumberEN.put("million", BigInteger.valueOf(1000000));
		bigWordToNumberEN.put("billion", BigInteger.valueOf(1000000000));
		bigWordToNumberEN.put("trillion", BigInteger.valueOf(1000000000000L));
		bigWordToNumberEN.put("quadrillion", BigInteger.valueOf(1000000000000000L));
		bigWordToNumberEN.put("quintillion", BigInteger.valueOf(1000000000000000000L));
		bigWordToNumberEN.put("sextillion", new BigInteger(sextillion));
		bigWordToNumberEN.put("septillion", new BigInteger(septillion));
		bigWordToNumberEN.put("octillion", new BigInteger(octillion));
		bigWordToNumberEN.put("nonillion", new BigInteger(nonillion));
		bigWordToNumberEN.put("decillion", new BigInteger(decillion));

	}

	public String numberToWord(BigInteger number, String locale) {
		
		StringBuilder words = new StringBuilder();
		
		// Converts numbers into words with selected language
		Map<BigInteger, String> numberToWordMap = null;
		if (locale.equals(turkishLocale)) {
			numberToWordMap = getNumberToWordMapTR();
		} else if (locale.equals(englishLocale)) {
			numberToWordMap = numberToWordMapEN;
		} else {
		}


		// Checking HashMap if the number is 0, negative or bigger digit
		if (number.equals(BigInteger.ZERO)) {
			words.append(numberToWordMap.get(number));
		} else if (number.compareTo(BigInteger.ZERO) < 0) {
			if (locale.equals(turkishLocale)) {
				words.append("eksi ");
			} else {
				words.append("minus ");
			}
			words.append(numberToWord(number.abs(), locale));
		} else if ((number.divide(new BigInteger(decillion))
				.compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(new BigInteger(decillion)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" desilyon ");
			} else {
				words.append(" decillion ");
			}
			if (!isModZero(number, new BigInteger(decillion))) {
				words.append(numberToWord(number.mod(new BigInteger(decillion)), locale));
			}
		} else if ((number.divide(new BigInteger(nonillion)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(new BigInteger(nonillion)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" nonilyon ");
			} else {
				words.append(" nonillion ");
			}
			if (!isModZero(number, new BigInteger(nonillion))) {
				words.append(numberToWord(number.mod(new BigInteger(nonillion)), locale));
			}
		} else if ((number.divide(new BigInteger(octillion)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(new BigInteger(octillion)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" oktilyon ");
			} else {
				words.append(" octillion ");
			}
			if (!isModZero(number, new BigInteger(octillion))) {
				words.append(numberToWord(number.mod(new BigInteger(octillion)), locale));
			}
		} else if ((number.divide(new BigInteger(septillion)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(new BigInteger(septillion)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" septilyon ");
			} else {
				words.append(" septillion ");
			}
			if (!isModZero(number, new BigInteger(septillion))) {
				words.append(numberToWord(number.mod(new BigInteger(septillion)), locale));
			}
		} else if ((number.divide(new BigInteger(sextillion)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(new BigInteger(sextillion)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" sekstilyon ");
			} else {
				words.append(" sextillion ");
			}
			if (!isModZero(number, new BigInteger(sextillion))) {
				words.append(numberToWord(number.mod(new BigInteger(sextillion)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000000000000000000L)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000000000000000000L)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" kentrilyon ");
			} else {
				words.append(" quintillion ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000000000000000000L))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000000000000000000L)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000000000000000L)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000000000000000L)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" katrilyon ");
			} else {
				words.append(" quadrillion ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000000000000000L))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000000000000000L)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000000000000L)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000000000000L)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" trilyon ");
			} else {
				words.append(" trillion ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000000000000L))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000000000000L)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000000000)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000000000)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" milyar ");
			} else {
				words.append(" billion ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000000000))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000000000)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000000)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000000)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" milyon ");
			} else {
				words.append(" million ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000000))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000000)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(1000)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(1000)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" bin ");
			} else {
				words.append(" thousand ");
			}
			if (!isModZero(number, BigInteger.valueOf(1000))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(1000)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(100)).compareTo(BigInteger.ZERO)) > 0) {
			words.append(numberToWord(number.divide(BigInteger.valueOf(100)), locale));
			if (locale.equals(turkishLocale)) {
				words.append(" yüz ");
			} else {
				words.append(" hundred ");
			}
			if (!isModZero(number, BigInteger.valueOf(100))) {
				words.append(numberToWord(number.mod(BigInteger.valueOf(100)), locale));
			}
		} else if ((number.divide(BigInteger.valueOf(10)).compareTo(BigInteger.ZERO)) > 0) {
			if (locale.equals(turkishLocale)) {
				words.append(getNumberToWordMapTR()
						.get(number.divide(BigInteger.valueOf(10)).multiply(BigInteger.valueOf(10))));
				words.append(" ");
				if (!isModZero(number, BigInteger.TEN)) {
					words.append(numberToWord(number.mod(BigInteger.TEN), locale));
				}

			} else {
				if (isNumberBetween(number)) {
					words.append(getNumberToWordMapEN().get(number));

				} else {
					words.append(getNumberToWordMapEN()
							.get(number.divide(BigInteger.valueOf(10)).multiply(BigInteger.valueOf(10))));
					words.append(" ");
					if (!isModZero(number, BigInteger.TEN)) {
						words.append(numberToWord(number.mod(BigInteger.TEN), locale));
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
	public BigInteger wordToNumber(String words, String locale) {

		Map<String, BigInteger> wordToNumberMap = null;
		if (locale.equals(turkishLocale)) {
			wordToNumberMap = wordToNumberMapTR;
		} else if (locale.equals(englishLocale)) {
			wordToNumberMap = wordToNumberMapEN;
		} else {
			return BigInteger.ZERO;
		}

		// Seperates words and puts into Array
		String[] wordArray = words.split(" ");

		BigInteger number = new BigInteger("0");
		BigInteger temp = new BigInteger("0");
		int negativeNumberCount = 0;

		// Converting String number into integer
		for (String word : wordArray) {
			BigInteger value = wordToNumberMap.get(word);
			if (value != null) {
				temp = temp.add(value);
			} else if (word.toLowerCase().equals("yüz") || word.toLowerCase().equals("hundred")) {
				if (wordArray[0].toLowerCase().equals("yüz")) {
					temp = BigInteger.valueOf(100);
				} else {
					temp = temp.multiply(BigInteger.valueOf(100));
				}
			} else if (word.toLowerCase().equals("bin") || word.toLowerCase().equals("thousand")) {
				temp = temp.multiply(BigInteger.valueOf(1000));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			} else if (word.toLowerCase().equals("milyon") || word.toLowerCase().equals("million")) {
				temp = temp.multiply(BigInteger.valueOf(1000000));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			} else if (word.toLowerCase().equals("milyar") || word.toLowerCase().equals("billion")) {
				temp = temp.multiply(BigInteger.valueOf(1000000000));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("trilyon") || word.toLowerCase().equals("trillion")) {
				temp = temp.multiply(BigInteger.valueOf(1000000000000L));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("katrilyon") || word.toLowerCase().equals("quadrillion")) {
				temp = temp.multiply(BigInteger.valueOf(1000000000000000L));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("kentrilyon") || word.toLowerCase().equals("quintillion")) {
				temp = temp.multiply(BigInteger.valueOf(1000000000000000000L));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("sekstilyon") || word.toLowerCase().equals("sextillion")) {
				temp = temp.multiply(new BigInteger("1000000000000000000000"));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("septilyon") || word.toLowerCase().equals("septillion")) {
				temp = temp.multiply(new BigInteger("1000000000000000000000000"));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("oktilyon") || word.toLowerCase().equals("octillion")) {
				temp = temp.multiply(new BigInteger("1000000000000000000000000000"));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("nonilyon") || word.toLowerCase().equals("nonillion")) {
				temp = temp.multiply(new BigInteger("1000000000000000000000000000000"));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			}else if (word.toLowerCase().equals("desilyon") || word.toLowerCase().equals("decillion")) {
				temp = temp.multiply(new BigInteger("1000000000000000000000000000000000"));
				number = number.add(temp);
				temp = BigInteger.ZERO;
			} else if (word.toLowerCase().equals("eksi") || word.toLowerCase().equals("minus")) {
				// temp *= -1;
				negativeNumberCount = 1;
			} else {
				continue;
			}
		}

		number = number.add(temp);

		// if number is negative return with number*-1
		if (negativeNumberCount == 1) {
			return number.multiply(BigInteger.valueOf(-1));
		}
		return number;

	}

	private boolean isNumberBetween(BigInteger number) {
		// checking if the EN number is between 10 and 20 because it has it's own word
		// unlike Turkish (eleven , twelve ...)
		if (number.compareTo(BigInteger.valueOf(10)) > 0 && BigInteger.valueOf(20).compareTo(number) > 0) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isModZero(BigInteger number1, BigInteger number2) {
		// Checking if the number1 %(mod) number2 is 0
		if (number1.mod(number2).equals(BigInteger.ZERO)) {
			return true;
		} else
			return false;

	}

	// Getter and setters for HashMaps
	public Map<BigInteger, String> getNumberToWordMapTR() {
		return numberToWordMapTR;
	}

	public void setNumberToWordMapTR(Map<BigInteger, String> numberToWordMapTR) {
		this.numberToWordMapTR = numberToWordMapTR;
	}

	public Map<BigInteger, String> getNumberToWordMapEN() {
		return numberToWordMapEN;
	}

	public void setNumberToWordMapEN(Map<BigInteger, String> numberToWordMapEN) {
		this.numberToWordMapEN = numberToWordMapEN;
	}

	public Map<BigInteger, String> getBigNumberToWordTR() {
		return bigNumberToWordTR;
	}

	public void setBigNumberToWordTR(Map<BigInteger, String> bigNumberToWordTR) {
		this.bigNumberToWordTR = bigNumberToWordTR;
	}

	public Map<BigInteger, String> getBigNumberToWordEN() {
		return bigNumberToWordEN;
	}

	public void setBigNumberToWordEN(Map<BigInteger, String> bigNumberToWordEN) {
		this.bigNumberToWordEN = bigNumberToWordEN;
	}

}
