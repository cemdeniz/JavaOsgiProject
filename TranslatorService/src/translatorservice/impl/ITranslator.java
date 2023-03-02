package translatorservice.impl;

public interface ITranslator {
	
	public String numberToWord(int number, String locale);
	public double wordToNumber(String word, String locale);

}
