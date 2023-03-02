package CalculatorGUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tranlatorservice.service.TranslatorService;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// new OSGi Service
	TranslatorService translatorService = new TranslatorService();

	//final variables
	private static final String turkishLocale = "tr_TR";

	// UI Components
	private JTextField number1Field;
	private JTextField number2Field;
	private JTextField resultField;
	private JButton addButton;
	private JButton subtractButton;
	private JButton multiplyButton;
	private JButton divideButton;
	private JLabel number1Label = new JLabel();
	private JLabel number2Label = new JLabel();
	private JLabel resultLabel = new JLabel();

	//global variables
	private String number1;
	private String number2;
	private String result = "";
	
	//new Locale
	private Locale currentLocale;

	public Calculator() {
		
		// Setting up default UI
		super("Calculator");
		setSize(870, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		currentLocale = new Locale("tr", "TR");
		createUI();
	}

	private void createUI() {
		// Language selection
		JLabel languageLabel = new JLabel("Select Language/Dil Seçin:");
		add(languageLabel);

		JButton enButton = new JButton("EN");
		enButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// currentLocale = Locale.ENGLISH; This doesn't work for other computers
				currentLocale = new Locale("en", "US");
				updateUI(currentLocale);
			}
		});
		add(enButton);

		JButton trButton = new JButton("TR");
		trButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("tr", "TR");
				updateUI(currentLocale);
			}
		});
		add(trButton);

		// Input fields
		number1Label = new JLabel(getLocalizedString(currentLocale, "number1"));
		add(number1Label);

		number1Field = new JTextField(10);
		add(number1Field);

		number2Label = new JLabel(getLocalizedString(currentLocale, "number2"));
		add(number2Label);

		number2Field = new JTextField(10);
		add(number2Field);

		// Result field
		resultLabel = new JLabel(getLocalizedString(currentLocale, "result"));
		add(resultLabel);

		resultField = new JTextField(10);
		resultField.setEditable(false);
		add(resultField);

		// Operation buttons
		addButton = new JButton(getLocalizedString(currentLocale, "addition"));
		addButton.addActionListener(this);
		add(addButton);

		subtractButton = new JButton(getLocalizedString(currentLocale, "subtraction"));
		subtractButton.addActionListener(this);
		add(subtractButton);

		multiplyButton = new JButton(getLocalizedString(currentLocale, "multiply"));
		multiplyButton.addActionListener(this);
		add(multiplyButton);

		divideButton = new JButton(getLocalizedString(currentLocale, "divide"));
		divideButton.addActionListener(this);
		add(divideButton);
	}

	private String getLocalizedString(Locale currentLocale, String key) {
		// Getting the title,button and field names from /resources/.properties file
		return ResourceBundle.getBundle("Calculator", currentLocale).getString(key);
	}

	private void updateUI(Locale currentLocale) {
		// updating Fields
		number1Field.setText("");
		number2Field.setText("");
		resultField.setText("");

		// Updating UI when language button clicked
		setTitle(getLocalizedString(currentLocale, "title"));
		number1Label.setText(getLocalizedString(currentLocale, "number1"));
		number2Label.setText(getLocalizedString(currentLocale, "number2"));
		resultLabel.setText(getLocalizedString(currentLocale, "result"));
		addButton.setText(getLocalizedString(currentLocale, "addition"));
		subtractButton.setText(getLocalizedString(currentLocale, "subtraction"));
		multiplyButton.setText(getLocalizedString(currentLocale, "multiply"));
		divideButton.setText(getLocalizedString(currentLocale, "divide"));
	}

	private String getNumber1(Locale currentLocale) {
		// Checks if the first input string is in our HashMaps, (if true) returns the String
		String number1 = number1Field.getText();
		if (!isNull(number1) && !isEmpty(number1)) {
			String[] wordArray = number1.split(" ");
			
			//Checking if there is "zero" or "sıfır" between numbers. ex: one hundred zero five + five
			for (String word : wordArray) {
				if (word.equals("sıfır") || word.equals("zero")) {
					if (wordArray.length>1) {
						number1 = null;
					}
				}
			}
			for (String word : wordArray) {
				if (currentLocale.getLanguage().equals("tr")
						&& !translatorService.getNumberToWordMapTR().containsValue(word)
						&& !translatorService.getBigNumberToWordTR().containsValue(word) && !(word.equals("eksi"))) {

					number1 = null;

				} else if (currentLocale.getLanguage().equals("en")
						&& !translatorService.getNumberToWordMapEN().containsValue(word)
						&& !translatorService.getBigNumberToWordEN().containsValue(word) && !(word.equals("minus"))) {

					number1 = null;

				}
			}
		} else {
			number1 = null;
		}

		return number1;

	}

	private String getNumber2(Locale currentLocale) {
		// Checks if the second input string is in our HashMaps, (if true) returns the String
		String number2 = number2Field.getText();
		if (!isNull(number2) && !isEmpty(number2)) {
			String[] word2Array = number2.split(" ");
			
			//Checking if there is "zero" or "sıfır" between numbers. ex: one hundred zero five + five
			for (String word2 : word2Array) {
				if (word2.equals("sıfır") || word2.equals("zero")) {
					if (word2Array.length>1) {
						number1 = null;
					}
				}
			}
			
			for (String word2 : word2Array) {
				if (currentLocale.getLanguage().equals("tr")
						&& !translatorService.getNumberToWordMapTR().containsValue(word2)
						&& !translatorService.getBigNumberToWordTR().containsValue(word2) && !(word2.equals("eksi"))) {

					number2 = null;

				} else if (currentLocale.getLanguage().equals("en")
						&& !translatorService.getNumberToWordMapEN().containsValue(word2)
						&& !translatorService.getBigNumberToWordEN().containsValue(word2) && !(word2.equals("minus"))) {

					number2 = null;

				}
			}
		} else {
			number2 = null;
		}

		return number2;
	}

	private void setResult(String result) {
		//Setting the result in UI
		resultField.setText(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 4 operation button listener
		
		String actionCommand = e.getActionCommand();
		double num1 = 0;
		double num2 = 0;
		double mathResult = 0;
		number1 = getNumber1(currentLocale);
		number2 = getNumber2(currentLocale);

		switch (actionCommand) {
		case "Addition":
		case "Topla":
			
			//addition method
			additionTopla(number1, number2, num1, num2, mathResult);
			break;

		case "Subtraction":
		case "Çıkar":
		
			//subtraction method
			subtractionCikar(number1, number2, num1, num2, mathResult);
			break;

		case "Multiplication":
		case "Çarp":
			
			//multiply method
			multiplyCarp(number1, number2, num1, num2, mathResult);
			break;

		case "Division":
		case "Böl":
		
			//division method
			divisionBol(number1, number2, num1, num2, mathResult);
			break;

		}
		setResult(result);
	}

	private void showWarning(Locale currentLocale) {
		//Throws a warning
		if (currentLocale.getLanguage().equals("tr")) {
			JOptionPane.showMessageDialog(null,
					"Sayılar yazı şeklinde girilmeli.\n" + "Girdiğiniz yazı dil ile uyumlu olmalı.\n"
							+ "Girdiğiniz sayı ve işlem sonucunuz Integer aralığında olmalı.\n",
					"Java OSGi Application ", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					"Please enter numbers in String form.\n" + "Your entry should match with language.\n"
							+ "Your number and result should be Integer.",
					"Java OSGi Application ", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void additionTopla(String number1, String number2, double num1, double num2, double mathResult) {
		//Calls translatorService.wordToNumber for converting the words into numbers to do math operation.
		//After operation calls translatorService.numberToWord to convert result into words to show result.
		
		if (!isNull(number1)) {
			num1 = translatorService.wordToNumber(number1, currentLocale.toString());
		}
		if (!isNull(number2)) {
			num2 = translatorService.wordToNumber(number2, currentLocale.toString());
		}
		if (!isNull(number1) && !isNull(number2)) {
			
			mathResult = num1 + num2;
			if (isIntegerInterval(mathResult)) {
				result = translatorService.numberToWord((int) num1 + (int) num2, currentLocale.toString());
			} else {
				showWarning(currentLocale);
			}
		} else {

			showWarning(currentLocale);
		}

	}

	private void subtractionCikar(String number1, String number2, double num1, double num2, double mathResult) {

		if (!isNull(number1)) {
			num1 = translatorService.wordToNumber(number1, currentLocale.toString());
		}
		if (!isNull(number2)) {
			num2 = translatorService.wordToNumber(number2, currentLocale.toString());
		}
		if (!isNull(number1) && !isNull(number2)) {
			//checking if operation result is Integer
			mathResult = num1 - num2;
			if (isIntegerInterval(mathResult)) {
				result = translatorService.numberToWord((int) num1 - (int) num2, currentLocale.toString());
			} else {
				showWarning(currentLocale);
			}
		} else {

			showWarning(currentLocale);
		}
	}

	private void multiplyCarp(String number1, String number2, double num1, double num2, double mathResult) {

		if (!isNull(number1)) {
			num1 = translatorService.wordToNumber(number1, currentLocale.toString());
		}
		if (!isNull(number2)) {
			num2 = translatorService.wordToNumber(number2, currentLocale.toString());
		}
		if (!isNull(number1) && !isNull(number2)) {
			//checking if operation result is Integer
			mathResult = num1 * num2;
			if (isIntegerInterval(mathResult)) {
				result = translatorService.numberToWord((int) num1 * (int) num2, currentLocale.toString());
			} else {
				showWarning(currentLocale);
			}
		} else {
			showWarning(currentLocale);
		}
		if (isEmpty(result)) {
			if (currentLocale.toString().equals(turkishLocale)) {
				result = "sıfır";
			} else {
				result = "zero";
			}
		}

	}

	private void divisionBol(String number1, String number2, double num1, double num2, double mathResult) {
		if (!isNull(number1)) {
			num1 = translatorService.wordToNumber(number1, currentLocale.toString());
		}
		if (!isNull(number2)) {
			num2 = translatorService.wordToNumber(number2, currentLocale.toString());
		}
		
		// If num2 is 0 in division, result is undefined: number/0 = "undefined"
		if (!isEmpty(isUndefined(num2, currentLocale))) {
			setResult(isUndefined(num2, currentLocale));
		}
		
		if (!isNull(number1) && !isNull(number2)) {
			//checking if operation result is Integer
			mathResult = num1 / num2;
			if (isIntegerInterval(mathResult)) {
				result = translatorService.numberToWord((int) num1 / (int) num2, currentLocale.toString());
			} else {
				showWarning(currentLocale);
			}
		} else {
			showWarning(currentLocale);
		}
		
	}

	private boolean isIntegerInterval(double number) {
		//checking if operation result is in integer interval -2147483648< ... <2147483647
		if (number < 2147483647 && -2147483648 < number) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNull(String number) {
		//cheking if the number is null
		if (number == null) {
			return true;
		}
		return false;
	}

	private boolean isEmpty(String number) {
		//cheking if the number is empty
		if (number.equals("")) {
			return true;
		}
		return false;
	}

	private String isUndefined(double number, Locale currentLocale) {
		//cheking if the number is undefined
		String result = "";
		if (number == 0.0 && currentLocale.getLanguage().equals("tr")) {
			result = "Tanımsız";
		} else if (number == 0.0 && currentLocale.getLanguage().equals("en")) {
			result = "Undefined";
		}
		return result;
	}

}
