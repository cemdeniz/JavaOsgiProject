package calculatorservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import CalculatorGUI.Calculator;
import tranlatorservice.service.TranslatorService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting CalculatorService");
		Activator.context = bundleContext;

		Calculator calculator = new Calculator();
		calculator.setVisible(true);

	} 

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping CalculatorService");

		// Release the TranslationService reference
		context.ungetService(context.getServiceReference(TranslatorService.class));

	}

}
