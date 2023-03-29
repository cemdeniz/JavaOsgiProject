package translatorservice.utils;

public class NumberToStringUtil {
	/*
	 * Each array represents a different category of numbers that can be used to convert numbers to their corresponding string representations.
	 * The ONES_EN, TENS_EN, TEENS_EN, and GROUPS_EN arrays represent the English language equivalents of single-digit numbers, tens, numbers between 11 and 19, and groups of three digits, respectively.
	 * Similarly, the ONES_TR, TENS_TR, TEENS_TR, and GROUPS_TR arrays represent the Turkish language equivalents of these categories.
	 */
    public static final String[] ONES_EN = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static final String[] TENS_EN = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    public static final String[] TEENS_EN = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] GROUPS_EN = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion", "octillion", "nonillion", "decillion"};
    
    public static final String[] ONES_TR = {"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
    public static final String[] TENS_TR = {"", "on", "yirmi", "otuz", "kırk", "elli", "atmış", "yetmiş", "seksen", "doksan"};
    public static final String[] TEENS_TR = {"", "on bir", "on iki", "on üç", "on dört", "on beş", "on altı", "on yedi", "on sekiz", "on dokuz"};
    public static final String[] GROUPS_TR = {"", "bin", "milyon", "milyar", "trilyon", "katrilyon", "kentilyon", "sekstilyon", "septilyon", "oktilyon", "nonilyon", "desilyon"};
    
}
