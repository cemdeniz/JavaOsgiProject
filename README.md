# JavaOsgiProject

Java SE version = 1.8

Java JDK version = 8

Recommended IDE = Eclipse

Overall, the application is composed of two OSGi services: one for translating numbers to words and words to numbers, and another for the user interface and basic math operations. 
The two services will communicate via interfaces defined in their respective bundles, and the user interface service will listen for changes to the active language preference and update the user interface accordingly. 
The application is be able to support Turkish and English languages, with Turkish being the default. Java Swing is used for the user interface and number arithmetic for the math operations up to Decillion numbers.

Note: if you multiply decillion number with another decillion number, you'll get "number decillion decillion" which means (number x decillion exponent decillion).

Note2: Removed the "bir" right before "yüz" in the result. This way TR result is more readable for some cases.

![osgiApp](https://user-images.githubusercontent.com/29862339/222452980-7a6bbf97-e906-477f-aadc-97e425d73761.png)

How to import?

	In Eclise IDE;
		-File -> Import -> select Git->Projects from Git -> Clone URl -> copy and paste this repositoriy's name(https://github.com/cemdeniz/JavaOsgiProject) into "URl: " field and click next until finish.

How to run?

	Right click CalculatorService -> Run As -> OSGi Framework
 
 
Possible Error fixes!

	"Unbound classpath container:" error;
		-Right click CalculatorService -> Properties -> Java Build Path -> in Libraries tab, double click your "JRE System Library" and select "Workspace default JRE".
	
	If you're missing "Eclipse Plugin Development Tools";
		-follow  this thread : https://stackoverflow.com/questions/46197398/eclipse-platform-plug-in-developer-guide-help-section-missing