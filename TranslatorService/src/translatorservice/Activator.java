package translatorservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import tranlatorservice.service.TranslatorService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	TranslatorService service = new TranslatorService();

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting TranslatorService");
		context = bundleContext;
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping TranslatorService");
		context = null;
	}

}
