package translatorservice.utils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class StringToNumberUtil {
	/*
	 * This class defines a utility for converting numbers written as strings into their corresponding numerical value. 
	 * The utility supports both English and Turkish language, and is able to convert numbers in the range of 0 to 10^33-1.
	 * The utility uses maps to define the number values for each word that can represent a number, such as "one", "two", "three", etc. 
	 * Additionally, it defines maps for common group words, such as "hundred", "thousand", "million", etc., which are used to construct larger numbers by combining the values of smaller ones.
	 */
		
    public static final Map<String, BigInteger> ONES_PLACE_EN = new HashMap<>();
    public static final Map<String, BigInteger> TENS_PLACE_EN = new HashMap<>();
    public static final Map<String, BigInteger> TEENS_PLACE_EN = new HashMap<>();
    public static final Map<String, BigInteger> GROUPS_PLACE_EN = new HashMap<>();
    
    public static final Map<String, BigInteger> ONES_PLACE_TR = new HashMap<>();
    public static final Map<String, BigInteger> TENS_PLACE_TR = new HashMap<>();
    public static final Map<String, BigInteger> TEENS_PLACE_TR = new HashMap<>();
    public static final Map<String, BigInteger> GROUPS_PLACE_TR = new HashMap<>();
    
    
    static {
    	// Initializing the English Maps
    	ONES_PLACE_EN.put("zero", BigInteger.ZERO);
    	ONES_PLACE_EN.put("one", BigInteger.ONE);
    	ONES_PLACE_EN.put("two", BigInteger.valueOf(2));
    	ONES_PLACE_EN.put("three", BigInteger.valueOf(3));
    	ONES_PLACE_EN.put("four", BigInteger.valueOf(4));
    	ONES_PLACE_EN.put("five", BigInteger.valueOf(5));
    	ONES_PLACE_EN.put("six", BigInteger.valueOf(6));
    	ONES_PLACE_EN.put("seven", BigInteger.valueOf(7));
    	ONES_PLACE_EN.put("eight", BigInteger.valueOf(8));
    	ONES_PLACE_EN.put("nine", BigInteger.valueOf(9));   	    	
        
        TEENS_PLACE_EN.put("eleven", BigInteger.valueOf(11));
        TEENS_PLACE_EN.put("twelve", BigInteger.valueOf(12));
        TEENS_PLACE_EN.put("thirteen", BigInteger.valueOf(13));
        TEENS_PLACE_EN.put("fourteen", BigInteger.valueOf(14));
        TEENS_PLACE_EN.put("fifteen", BigInteger.valueOf(15));
        TEENS_PLACE_EN.put("sixteen", BigInteger.valueOf(16));
        TEENS_PLACE_EN.put("seventeen", BigInteger.valueOf(17));
        TEENS_PLACE_EN.put("eighteen", BigInteger.valueOf(18));
        TEENS_PLACE_EN.put("nineteen", BigInteger.valueOf(19));

        TENS_PLACE_EN.put("ten", BigInteger.TEN);
        TENS_PLACE_EN.put("twenty", BigInteger.valueOf(20));
        TENS_PLACE_EN.put("thirty", BigInteger.valueOf(30));
        TENS_PLACE_EN.put("forty", BigInteger.valueOf(40));
        TENS_PLACE_EN.put("fifty", BigInteger.valueOf(50));
        TENS_PLACE_EN.put("sixty", BigInteger.valueOf(60));
        TENS_PLACE_EN.put("seventy", BigInteger.valueOf(70));
        TENS_PLACE_EN.put("eighty", BigInteger.valueOf(80));
        TENS_PLACE_EN.put("ninety", BigInteger.valueOf(90));

        GROUPS_PLACE_EN.put("hundred", BigInteger.valueOf(100));
        GROUPS_PLACE_EN.put("thousand", BigInteger.valueOf(1000));
        GROUPS_PLACE_EN.put("million", BigInteger.valueOf(1000000));
        GROUPS_PLACE_EN.put("billion", BigInteger.valueOf(1000000000));
        GROUPS_PLACE_EN.put("trillion", BigInteger.valueOf(1000000000000L));
        GROUPS_PLACE_EN.put("quadrillion", BigInteger.valueOf(1000000000000000L));
        GROUPS_PLACE_EN.put("quintillion", BigInteger.valueOf(1000000000000000000L));
        GROUPS_PLACE_EN.put("sextillion", new BigInteger("1000000000000000000000"));
        GROUPS_PLACE_EN.put("septillion", new BigInteger("1000000000000000000000000"));
        GROUPS_PLACE_EN.put("octillion", new BigInteger("1000000000000000000000000000"));
        GROUPS_PLACE_EN.put("nonillion", new BigInteger("1000000000000000000000000000000"));
        GROUPS_PLACE_EN.put("decillion", new BigInteger("1000000000000000000000000000000000"));
        
     // Initializing the Turkish Maps
        ONES_PLACE_TR.put("sıfır", BigInteger.ZERO);
        ONES_PLACE_TR.put("bir", BigInteger.ONE);
        ONES_PLACE_TR.put("iki", BigInteger.valueOf(2));
        ONES_PLACE_TR.put("üç", BigInteger.valueOf(3));
        ONES_PLACE_TR.put("dört", BigInteger.valueOf(4));
        ONES_PLACE_TR.put("beş", BigInteger.valueOf(5));
        ONES_PLACE_TR.put("altı", BigInteger.valueOf(6));
        ONES_PLACE_TR.put("yedi", BigInteger.valueOf(7));
    	ONES_PLACE_TR.put("sekiz", BigInteger.valueOf(8));
    	ONES_PLACE_TR.put("dokuz", BigInteger.valueOf(9)); 

        TENS_PLACE_TR.put("on", BigInteger.TEN);
        TENS_PLACE_TR.put("yirmi", BigInteger.valueOf(20));
        TENS_PLACE_TR.put("otuz", BigInteger.valueOf(30));
        TENS_PLACE_TR.put("kırk", BigInteger.valueOf(40));
        TENS_PLACE_TR.put("elli", BigInteger.valueOf(50));
        TENS_PLACE_TR.put("atmış", BigInteger.valueOf(60));
        TENS_PLACE_TR.put("yetmiş", BigInteger.valueOf(70));
        TENS_PLACE_TR.put("seksen", BigInteger.valueOf(80));
        TENS_PLACE_TR.put("doksan", BigInteger.valueOf(90));

        GROUPS_PLACE_TR.put("yüz", BigInteger.valueOf(100));
        GROUPS_PLACE_TR.put("bin", BigInteger.valueOf(1000));
        GROUPS_PLACE_TR.put("milyon", BigInteger.valueOf(1000000));
        GROUPS_PLACE_TR.put("milyar", BigInteger.valueOf(1000000000));
        GROUPS_PLACE_TR.put("trilyon", BigInteger.valueOf(1000000000000L));
        GROUPS_PLACE_TR.put("katrilyon", BigInteger.valueOf(1000000000000000L));
        GROUPS_PLACE_TR.put("kentrilyon", BigInteger.valueOf(1000000000000000000L));
        GROUPS_PLACE_TR.put("sekstilyon", new BigInteger("1000000000000000000000"));
        GROUPS_PLACE_TR.put("septilyon", new BigInteger("1000000000000000000000000"));
        GROUPS_PLACE_TR.put("oktilyon", new BigInteger("1000000000000000000000000000"));
        GROUPS_PLACE_TR.put("nonilyon", new BigInteger("1000000000000000000000000000000"));
        GROUPS_PLACE_TR.put("desilyon", new BigInteger("1000000000000000000000000000000000"));
        
        
    }

   
}

