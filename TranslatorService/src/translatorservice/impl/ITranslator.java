package translatorservice.impl;

import java.math.BigInteger;

public interface ITranslator {
	
	public String numberToWord(BigInteger number, String locale);
	public BigInteger wordToNumber(String word, String locale);

}
