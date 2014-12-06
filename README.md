==========================
  Email Service Plugin
==========================

This code was extracted from a big web application, and the idea is to encapsulate all functionality about sending emails, creating PDF attachments, and configure how to send emails in one separate jar.

********************
How does it work?
********************

The e-mail body is based in an Apache Velocity File (.vm). All the text used in the .vm file must me specified in a "messages_XX.properties" (i18n support to create e-mails in different languages).
The configuration for the email service is located in the application.properties file.
An application would only need to add this jar dependency into its pom, and use the objects it provides to create an e-mail and send it.

Maven Dependency

```
<dependency>
  <groupId>com.store.emailservice</groupId>
  <artifactId>store-email-service</artifactId>
  <version>1.0</version>
</dependency>
```

********************
How to create an email?
********************

1) Inject EmailService

```
@Autowired
EmailService emailService;
```
2) Create an EmailWrapper object

```
// Choose the language you'd like to create the e-mail
Locale locale = new Locale("en");

// Add vm parameters
TemplateData templateData = new TemplateData(messageSource, locale);
templateData.add("username", USERNAME);
templateData.add("urlInfo", URL_REPO);

// Create an emailWrapper object
EmailWrapper email = new EmailWrapper(to, subject, "test.vm", templateData);
//Inject the EmailService and send it
emailService.sendSimpleEmail(email, locale);
```

********************
 MISSING FEATURES 
********************
The idea is in the short future implement the following features:

-Externalize email service configuration 
-Create PDF files from .HTML, and attach it to emails
