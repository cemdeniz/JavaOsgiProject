package translatorservice.impl;

import java.math.BigInteger;

public interface ITranslator {
	/*
	 * The numberToWord method takes a BigInteger object representing a number and a String object representing the desired locale. It returns a String object representing the word form of the input number in the specified locale.
	 * 
	 * The wordToNumber method takes a String object representing a word and a String object representing the desired locale. It returns a BigInteger object representing the numeric value of the input word in the specified locale.
	 */
	
	public String numberToWord(BigInteger number, String locale);
	public BigInteger wordToNumber(String word, String locale);

}
