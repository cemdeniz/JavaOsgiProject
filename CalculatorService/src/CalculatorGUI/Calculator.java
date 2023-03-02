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

	private static final String turkishLocale = "tr_TR";
	private static final String englishLocale = "en_US";

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

	//
	private String number1;
	private String number2;
	private String result = "";

	private Locale currentLocale;

	public Calculator() {
		super("Calculator");
		setSize(870, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		// Setting up default UI
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
		// Returning first number input
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
		// Returning second number input
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
		resultField.setText(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		// Math Button action listener
		number1 = getNumber1(currentLocale);
		number2 = getNumber2(currentLocale);

		double num1 = 0;
		double num2 = 0;
		double mathResult = 0;

		switch (actionCommand) {
		case "Addition":
		case "Topla":
		
			additionTopla(number1, number2, num1, num2, mathResult);
			break;

		case "Subtraction":
		case "Çıkar":
		
			subtractionCikar(number1, number2, num1, num2, mathResult);
			break;

		case "Multiplication":
		case "Çarp":
			
			multiplyCarp(number1, number2, num1, num2, mathResult);
			break;

		case "Division":
		case "Böl":
		
			divisionBol(number1, number2, num1, num2, mathResult);
			break;

		}
		setResult(result);
	}

	private void showWarning(Locale currentLocale) {
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

		if (!isNull(number1)) {
			num1 = translatorService.wordToNumber(number1, currentLocale.toString());
		}
		if (!isNull(number2)) {
			num2 = translatorService.wordToNumber(number2, currentLocale.toString());
		}
		if (!isNull(number1) && !isNull(number2)) {
			//checking if operation result is Integer
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
		if (number < 2147483647 && -2147483648 < number) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isNull(String number) {
		if (number == null) {
			return true;
		}
		return false;

	}

	private boolean isEmpty(String number) {
		if (number.equals("")) {
			return true;
		}
		return false;
	}

	private String isUndefined(double number, Locale currentLocale) {
		String result = "";
		if (number == 0.0 && currentLocale.getLanguage().equals("tr")) {
			result = "Tanımsız";
		} else if (number == 0.0 && currentLocale.getLanguage().equals("en")) {
			result = "Undefined";
		}
		return result;
	}

}
