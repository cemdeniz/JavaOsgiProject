package CalculatorGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tranlatorservice.service.TranslatorService;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// new OSGi Service
	TranslatorService translatorService = new TranslatorService();

	// final variables
	private final BigInteger unDesillion = new BigInteger("1000000000000000000000000000000000000");

	// UI Components
	private JTextField number1Field;
	private JTextField number2Field;
	private JTextField resultField;
	private JButton addButton;
	private JButton subtractButton;
	private JButton multiplyButton;
	private JButton divideButton;
	private JButton clearButton;
	private JLabel number1Label = new JLabel();
	private JLabel number2Label = new JLabel();
	private JLabel resultLabel = new JLabel();
	private JLabel languageLabel = new JLabel();
	private JLabel operationsLabel = new JLabel();

	// global variables
	private String number1;
	private String number2;
	private String result = "";
	private Locale currentLocale;

	public Calculator() {
		/**
		 * Constructs a new Calculator object with default UI settings. Sets the title
		 * of the JFrame to "Calculator", size to (1107, 215), and layout to FlowLayout.
		 * The default language is Turkish (tr-TR). Calls the createUI() method to
		 * create the user interface.
		 */
		super("Calculator");
		setSize(900, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		
		currentLocale = Locale.getDefault(); // getting language setting from windows
		if (!currentLocale.getLanguage().equals("tr")) {
			// setting default language to TR
			Locale.setDefault(new Locale("tr", "TR"));
		}
		currentLocale = Locale.getDefault();
		createUI();
	}

	private void createUI() {
	        /**
	         * Creates the user interface for the calculator.
	         */

	        // Set up layout manager
	        GridBagLayout layout = new GridBagLayout();
	        GridBagConstraints c = new GridBagConstraints();
	        
	        setLayout(layout);
	        
	        setMinimumSize(new Dimension(650,275));
	        
	        c.insets = new Insets(5, 5, 5, 5);
	        
	        // Language selection
	        languageLabel = new JLabel(getLocalizedString(currentLocale, "language"));
	        languageLabel.setFont(new Font("Arial", Font.BOLD, 15));
	        c.gridx = 0; c.gridy = 0;
	        c.ipadx = 10; c.ipady = 10;
	        c.anchor = GridBagConstraints.EAST;
	        
	        add(languageLabel, c);

	        // Buttons to switch language
	        JButton enButton = new JButton("EN");
	        enButton.setBackground(Color.lightGray);
	        enButton.setPreferredSize(new Dimension(65, 25));
	        enButton.setBorder(new LineBorder(Color.BLACK));
	        enButton.setFont(new Font("Arial", Font.PLAIN, 15));
	        enButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Switch to English locale
	                currentLocale = new Locale("en", "US");
	                updateUI(currentLocale);
	            }
	        });
	        c.gridx = 1; c.gridy = 0;

	        add(enButton, c);

	        JButton trButton = new JButton("TR");
	        trButton.setBackground(Color.lightGray);
	        trButton.setPreferredSize(new Dimension(65, 25));
	        trButton.setBorder(new LineBorder(Color.BLACK));
	        trButton.setFont(new Font("Arial", Font.PLAIN, 15));
	        trButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Switch to Turkish locale
	                currentLocale = new Locale("tr", "TR");
	                updateUI(currentLocale);
	            }
	        });
	        c.gridx = 2; c.gridy = 0;
	        add(trButton, c);

	        // Input fields
	        number1Label = new JLabel(getLocalizedString(currentLocale, "number1"));
	        number1Label.setFont(new Font("Arial", Font.BOLD, 15));
	        c.gridx = 0; c.gridy = 1;
	        add(number1Label, c);

	        number1Field = new JTextField(30);
	        number1Field.setBackground(Color.lightGray);
	        number1Field.setBorder(new LineBorder(Color.BLACK));
	        number1Field.setBorder(BorderFactory.createCompoundBorder(
	        		number1Field.getBorder(), 
	                BorderFactory.createEmptyBorder(3, 10, 3, 5)));
	        c.gridx = 1; c.gridy = 1;
	        c.gridwidth = 4;
	        add(number1Field, c);

	        number2Label = new JLabel(getLocalizedString(currentLocale, "number2"));
	        number2Label.setFont(new Font("Arial", Font.BOLD, 15));
	        c.gridx = 0; c.gridy = 2;
	        c.gridwidth = 1;
	        add(number2Label, c);

	        number2Field = new JTextField(30);
	        number2Field.setBackground(Color.lightGray);
	        number2Field.setBorder(new LineBorder(Color.BLACK));
	        number2Field.setBorder(BorderFactory.createCompoundBorder(
	        		number2Field.getBorder(), 
	                BorderFactory.createEmptyBorder(3, 10, 3, 5)));
	        c.gridx = 1; c.gridy = 2;
	        c.gridwidth = 4;
	        add(number2Field, c);

	        // Result field
	        resultLabel = new JLabel(getLocalizedString(currentLocale, "result"));
	        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));
	        c.gridx = 0; c.gridy = 3;
	        c.gridwidth = 1;
	        add(resultLabel, c);

	        resultField = new JTextField(30);
	        resultField.setEditable(false);
	        resultField.setBackground(Color.lightGray);
	        resultField.setBorder(new LineBorder(Color.BLACK));
	        resultField.setBorder(BorderFactory.createCompoundBorder(
	        		resultField.getBorder(), 
	                BorderFactory.createEmptyBorder(3, 10, 3, 5)));
	        c.gridx = 1; c.gridy = 3;
	        c.gridwidth = 4;
	        add(resultField, c);
	       
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.insets = new Insets(12,5,5,5);
	        // Operation buttons
	        addButton = new JButton(getLocalizedString(currentLocale, "addition"));
	        addButton.addActionListener(this);
	        addButton.setBackground(Color.lightGray);
	        addButton.setBorder(new LineBorder(Color.BLACK));
	        addButton.setPreferredSize(new Dimension(65, 25));
	        c.gridx = 1; c.gridy = 4;
	        c.gridwidth = 1;
	        add(addButton, c);

	        subtractButton = new JButton(getLocalizedString(currentLocale, "subtraction"));
	        subtractButton.addActionListener(this);
	        subtractButton.setBackground(Color.lightGray);
	        subtractButton.setBorder(new LineBorder(Color.BLACK));
	        subtractButton.setPreferredSize(new Dimension(75, 25));
	        c.gridx = 2; c.gridy = 4;
	        c.gridwidth = 1;
	        add(subtractButton, c);

	        multiplyButton = new JButton(getLocalizedString(currentLocale, "multiply"));
	        multiplyButton.addActionListener(this);
	        multiplyButton.setBackground(Color.lightGray);
	        multiplyButton.setBorder(new LineBorder(Color.BLACK));
	        multiplyButton.setPreferredSize(new Dimension(75, 25));
	        c.gridx = 3; c.gridy = 4;
	        c.gridwidth = 1;
	        add(multiplyButton, c);

	        divideButton = new JButton(getLocalizedString(currentLocale, "divide"));
	        divideButton.addActionListener(this);
	        divideButton.setBackground(Color.lightGray);
	        divideButton.setPreferredSize(new Dimension(65, 25));
	        divideButton.setBorder(new LineBorder(Color.BLACK));
	        c.gridx = 4; c.gridy = 4;
	        c.gridwidth = 1;
	        add(divideButton, c);

	        clearButton = new JButton(getLocalizedString(currentLocale, "clear"));
	        clearButton.addActionListener(this);
	        clearButton.setBackground(Color.lightGray);
	        clearButton.setPreferredSize(new Dimension(65, 25));
	        clearButton.setBorder(new LineBorder(Color.BLACK));
	        c.gridx = 5; c.gridy = 4;
	        add(clearButton, c);
	        
	    }

	private String getLocalizedString(Locale currentLocale, String key) {
		/**
		 * Retrieves a localized string for a given key from a ResourceBundle file named
		 * "Calculator.properties" located in the "/resources/" directory.
		 *
		 * @param currentLocale the locale for the desired language
		 * @param key           the identifier for the localized string
		 * @return the localized string for the given key
		 */
		return ResourceBundle.getBundle("Calculator", currentLocale).getString(key);
	}

	private void updateUI(Locale currentLocale) {
		/**
		 * Clears input and output fields, and updates the UI with localized texts.
		 * 
		 * @param currentLocale the current locale to use for language localization
		 */
		// updating Fields
		number1Field.setText("");
		number2Field.setText("");
		resultField.setText("");

		// Updating UI when language button clicked
		setTitle(getLocalizedString(currentLocale, "title"));
		languageLabel.setText(getLocalizedString(currentLocale, "language"));
		number1Label.setText(getLocalizedString(currentLocale, "number1"));
		number2Label.setText(getLocalizedString(currentLocale, "number2"));
		resultLabel.setText(getLocalizedString(currentLocale, "result"));
		operationsLabel.setText(getLocalizedString(currentLocale, "operations"));
		addButton.setText(getLocalizedString(currentLocale, "addition"));
		subtractButton.setText(getLocalizedString(currentLocale, "subtraction"));
		multiplyButton.setText(getLocalizedString(currentLocale, "multiply"));
		divideButton.setText(getLocalizedString(currentLocale, "divide"));
		clearButton.setText(getLocalizedString(currentLocale, "clear"));
	}

	private String getFirstNumber(Locale currentLocale) {
		/**
		 * 
		 * This method retrieves the first number entered by the user from the
		 * number1Field
		 * 
		 * text field, and validates it against the current locale. It returns the
		 * validated
		 * 
		 * number as a string, or null if the number is invalid.
		 * 
		 * @param currentLocale the current locale of the application
		 * 
		 * @return a validated number string, or null if the number is invalid
		 */

		String number1 = number1Field.getText().toLowerCase();
		
		boolean ValidEdgeCase = translatorService.validateEdgeCases(number1, currentLocale.getLanguage());
		
		if (!isNull(number1) && !isEmpty(number1) && ValidEdgeCase) {
			boolean isValid = Arrays.stream(number1.split(" ")).allMatch(word -> (currentLocale.getLanguage()
					.equals("tr")
					&& (translatorService.validateNumber(word, currentLocale.getLanguage()) || word.equals("eksi")))
					|| (currentLocale.getLanguage().equals("en")
							&& (translatorService.validateNumber(word, currentLocale.getLanguage())
									|| word.equals("minus"))));

			if (!isValid) {
				number1 = "notValid";
			}
		} else {	
			number1 = !ValidEdgeCase ? "notValid" : null ;
		}

		return number1;

	}

	private String getSecondNumber(Locale currentLocale) {
		/**
		 * 
		 * This method retrieves the second number entered by the user from the
		 * number2Field
		 * 
		 * text field, and validates it against the current locale. It returns the
		 * validated
		 * 
		 * number as a string, or null if the number is invalid.
		 * 
		 * @param currentLocale the current locale of the application
		 * 
		 * @return a validated number string, or null if the number is invalid
		 */
		String number2 = number2Field.getText().toLowerCase();
		
		boolean ValidEdgeCase2 = translatorService.validateEdgeCases(number2, currentLocale.getLanguage());
		
		if (!isNull(number2) && !isEmpty(number2) && ValidEdgeCase2) {
			boolean isValid = Arrays.stream(number2.split(" ")).allMatch(word -> (currentLocale.getLanguage()
					.equals("tr")
					&& (translatorService.validateNumber(word, currentLocale.getLanguage()) || word.equals("eksi")))
					|| (currentLocale.getLanguage().equals("en")
							&& (translatorService.validateNumber(word, currentLocale.getLanguage())
									|| word.equals("minus"))));

			if (!isValid) {
				number2 = "notValid";
			}
		} else {
			number1 = !ValidEdgeCase2 ? "notValid" : null ;
		}

		return number2;

	}

	private void setResult(String result) {

		// Setting the result in UI,
		// with replaceAll erasing extra " " (space) between words.
		resultField.setText(eraseElement(result).replaceAll("\\s+", " "));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * 
		 * This method listens for button clicks and performs the corresponding
		 * 
		 * mathematical operation. It sets the result of the operation and updates
		 * 
		 * the user interface.
		 * 
		 * @param e the action event generated by the button click
		 */
		String actionCommand = e.getActionCommand();
		
		// Initialize variables for the operands and result of the mathematical operation
		BigInteger num1 = BigInteger.ZERO;
		BigInteger num2 = BigInteger.ZERO;
		BigInteger mathResult = BigInteger.ZERO;
		number1 = getFirstNumber(currentLocale);
		number2 = getSecondNumber(currentLocale);

		switch (actionCommand) {
		case "Add":
		case "Topla":

			// addition method
			add(number1, number2, num1, num2, mathResult);
			break;

		case "Subtract":
		case "Çıkar":

			// subtraction method
			subtract(number1, number2, num1, num2, mathResult);
			break;

		case "Multiply":
		case "Çarp":

			// multiply method
			multiply(number1, number2, num1, num2, mathResult);
			break;

		case "Divide":
		case "Böl":

			// division method
			divide(number1, number2, num1, num2, mathResult);
			break;
		case "Clear":
		case "Temizle":

			// Clearing inputs
			result = "";
			updateUI(currentLocale);
			break;

		}
		setResult(result);
	}

	private void showWarning(Locale currentLocale, String errorType) {
		/**
		 * 
		 * This method displays a warning message to the user, depending on the current
		 * locale
		 * 
		 * of the application. The warning message provides guidelines for entering
		 * valid input,
		 * 
		 * and clears the result field.
		 * 
		 * @param currentLocale the current locale of the application
		 */

		// Clear the result
		result = "";
		setResult(result);
		// Throws a warning
		switch (errorType) {
		case "null":
			if (currentLocale.getLanguage().equals("tr")) {
				JOptionPane.showMessageDialog(null, "Sayı alanlarını boş bırakmayınız!", "Hata Aldınız!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Please don't leave number fields empty.", "Error Message!",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "notValid":
			if (currentLocale.getLanguage().equals("tr")) {
				JOptionPane.showMessageDialog(null, "Yanlış sayı girdiniz. Tekrar deneyin!", "Hata Aldınız!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "You entered wrong number. Try again!", "Error Message!",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "outLimit":
			if (currentLocale.getLanguage().equals("tr")) {
				JOptionPane.showMessageDialog(null, "İşlem sonucu Desilyon basamak üstüne çıkamaz!", "Hata Aldınız!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Your result can't be higher Desillion digit!", "Error Message!",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}

	}

	private void add(String number1, String number2, BigInteger num1, BigInteger num2, BigInteger mathResult) {
		/**
		 * 
		 * This method takes two numbers in word form, converts them to BigInteger
		 * values using the
		 * 
		 * translatorService.wordToNumber method, performs addition on these values, and
		 * converts
		 * 
		 * the result back to a word using the translatorService.numberToWord method. If
		 * the
		 * 
		 * resulting value is greater than or equal to unDecillion (10^36), a warning
		 * message is
		 * 
		 * displayed using the showWarning method.
		 * 
		 * @param number1    the first number in word form
		 * 
		 * @param number2    the second number in word form
		 * 
		 * @param num1       a BigInteger representation of the first number
		 * 
		 * @param num2       a BigInteger representation of the second number
		 * 
		 * @param mathResult the result of the math operation
		 */

		// Before any operation, checking if the numbers are valid or else show warning.
		if (number1 == "notValid" || number2 == "notValid") {
			showWarning(currentLocale, "notValid");
			return;
		}

		num1 = Optional.ofNullable(number1).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);

		num2 = Optional.ofNullable(number2).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);
		if (num1 != null && num2 != null) {
			// calculating the math operation
			mathResult = num1.add(num2);

			// If result reaches to unDecillion(10^36) throw error
			if (mathResult.compareTo(unDesillion) >= 0) {
				showWarning(currentLocale, "outLimit");
				return;
			}
			// sending result to return as Words
			result = translatorService.numberToWord(mathResult, currentLocale.getLanguage());
		} else
			showWarning(currentLocale, "null");
	}

	private void subtract(String number1, String number2, BigInteger num1, BigInteger num2, BigInteger mathResult) {
		/**
		 * 
		 * This method takes two numbers in word form, converts them to BigInteger
		 * values using the
		 * 
		 * translatorService.wordToNumber method, performs subtraction on these values,
		 * and converts
		 * 
		 * the result back to a word using the translatorService.numberToWord method. If
		 * the
		 * 
		 * resulting value is greater than or equal to unDecillion (10^36), a warning
		 * message is
		 * 
		 * displayed using the showWarning method.
		 * 
		 * @param number1    the first number in word form
		 * 
		 * @param number2    the second number in word form
		 * 
		 * @param num1       a BigInteger representation of the first number
		 * 
		 * @param num2       a BigInteger representation of the second number
		 * 
		 * @param mathResult the result of the math operation
		 */

		// Before any operation, checking if the numbers are valid or else show warning.
		if (number1 == "notValid" || number2 == "notValid") {
			showWarning(currentLocale, "notValid");
			return;
		}

		num1 = Optional.ofNullable(number1).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);

		num2 = Optional.ofNullable(number2).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);
		if (num1 != null && num2 != null) {
			// calculating the math operation
			mathResult = num1.subtract(num2);

			// If result reaches to unDecillion(10^36) throw error
			if (mathResult.compareTo(unDesillion) >= 0) {
				showWarning(currentLocale, "outLimit");
				return;
			}
			// sending result to return as Words
			result = translatorService.numberToWord(mathResult, currentLocale.getLanguage());
		} else
			showWarning(currentLocale, "null");
	}

	private void multiply(String number1, String number2, BigInteger num1, BigInteger num2, BigInteger mathResult) {

		/**
		 * 
		 * This method takes two numbers in word form, converts them to BigInteger
		 * values using the
		 * 
		 * translatorService.wordToNumber method, performs multiplication on these
		 * values, and converts
		 * 
		 * the result back to a word using the translatorService.numberToWord method. If
		 * the
		 * 
		 * resulting value is greater than or equal to unDecillion (10^36), a warning
		 * message is
		 * 
		 * displayed using the showWarning method.
		 * 
		 * @param number1    the first number in word form
		 * 
		 * @param number2    the second number in word form
		 * 
		 * @param num1       a BigInteger representation of the first number
		 * 
		 * @param num2       a BigInteger representation of the second number
		 * 
		 * @param mathResult the result of the math operation
		 */

		// Before any operation, checking if the numbers are valid or else show warning.
		if (number1 == "notValid" || number2 == "notValid") {
			showWarning(currentLocale, "notValid");
			return;
		}

		num1 = Optional.ofNullable(number1).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);

		num2 = Optional.ofNullable(number2).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);
		if (num1 != null && num2 != null) {
			// calculating the math operation
			mathResult = num1.multiply(num2);

			// If result reaches to unDecillion(10^36) throw error
			if (mathResult.compareTo(unDesillion) >= 0) {
				showWarning(currentLocale, "outLimit");
				return;
			}
			// sending result to return as Words
			result = translatorService.numberToWord(mathResult, currentLocale.getLanguage());
		} else
			showWarning(currentLocale, "null");

	}

	private void divide(String number1, String number2, BigInteger num1, BigInteger num2, BigInteger mathResult) {
		/**
		 * 
		 * Performs division operation on two numbers given as words and sets the result
		 * as a word if it's within the allowed range,
		 * 
		 * otherwise throws an error message.
		 * 
		 * @param number1    The first number as a word
		 * 
		 * @param number2    The second number as a word
		 * 
		 * @param num1       The first number as a numeric value
		 * 
		 * @param num2       The second number as a numeric value
		 * 
		 * @param mathResult The result of the division operation
		 */

		// Before any operation, checking if the numbers are valid or else show warning.
		if (number1 == "notValid" || number2 == "notValid") {
			showWarning(currentLocale, "notValid");
			return;
		}

		num1 = Optional.ofNullable(number1).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);

		num2 = Optional.ofNullable(number2).map(num -> translatorService.wordToNumber(num, currentLocale.getLanguage()))
				.orElse(null);

		// Check if both numbers are not null
		if (num1 != null && num2 != null) {
			// If num2 is 0 in division, result is undefined: number/0 = "undefined"
			String undefined = isUndefined(num1, num2, currentLocale);
			if (!isEmpty(undefined)) {
				result = undefined;
			} else {
				// Calculate the math operation
				mathResult = num1.divide(num2);

				// If result is within the allowed range, convert to words and set as result
				if (mathResult.compareTo(unDesillion) < 0) {
					result = translatorService.numberToWord(mathResult, currentLocale.getLanguage());
				} else {
					showWarning(currentLocale, "outLimit");
				}
			}
		} else {
			showWarning(currentLocale, "null");
		}

	}

	private boolean isNull(String number) {
		/**
		 * Checks whether the input string is null or not.
		 * 
		 * @param number the input string to be checked for nullity
		 * @return true if the input string is null, false otherwise
		 */
		return number == null ? true : false;
	}

	private boolean isEmpty(String number) {
		/**
		 * Checks if a given string is empty or not.
		 * 
		 * @param number the string to check
		 * @return true if the string is empty, false otherwise
		 */
		return number.equals("") ? true : false;
	}

	private String isUndefined(BigInteger num1, BigInteger num2, Locale currentLocale) {
		/**
		 * Checks if the given BigInteger number2 is equal to zero, which would result
		 * in an undefined value in division operations.
		 * 
		 * @param number        The BigInteger number to check for zero value.
		 * @param currentLocale The current Locale being used for language-specific
		 *                      text.
		 * @return A String representing "Undefined" in English, or "Tanımsız" in
		 *         Turkish, if the given number is equal to zero; otherwise an empty
		 *         String is returned.
		 */

		String result = "";
		if (num1.compareTo(BigInteger.ZERO) == 0 && num2.compareTo(BigInteger.ZERO) == 0) {
			result = currentLocale.getLanguage().equals("tr") ? "Belirsiz" : "Undefined";
			return result;
		} else if (num2.compareTo(BigInteger.ZERO) == 0) {
			result = currentLocale.getLanguage().equals("tr") ? "Tanımsız" : "Undefined";
		}
		return result;
	}

	private String eraseElement(String result) {
		/**
		 * Removes the word "bir" if it comes right before the word "yüz". This makes
		 * the Turkish result more readable for some cases.
		 *
		 * @param result the string to erase an element from
		 * @return the updated string with the element removed
		 */
		String[] resultArray = result.split(" ");
		for (int i = 0; i < resultArray.length - 1; i++) {
			if (resultArray[i].equals("bir") && resultArray[i + 1].equals("yüz")) {
				resultArray[i] = "";
			}
		}
		return String.join(" ", resultArray);
	}

}
