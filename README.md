# JavaOsgiProject
Java Osgi Project

Java SE version = 1.8
Java Sdk version = 8

Overall, the application is composed of two OSGi services: one for translating numbers to words and words to numbers, and another for the user interface and basic math operations. 
The two services will communicate via interfaces defined in their respective bundles, and the user interface service will listen for changes to the active language preference and update the user interface accordingly. 
The application is be able to support Turkish and English languages, with Turkish being the default. Java Swing is used for the user interface and integer arithmetic for the math operations.