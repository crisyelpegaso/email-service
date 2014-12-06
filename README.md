==========================
  Email Service Plugin
==========================

This code was extracted from a big web application, and the idea is to encapsulate all functionality about sending emails, creating PDF attachments, and configure how to send emails in one separate jar.
It uses Apache Velocity to create the content of emails, Java 1.7 and Spring for dependency injection.

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
1) Create a .vm file with the email body content

```
## This will include #msg macro to support i18n
#parse("header.vm")

  <html>
    <head>
      <title>Email Service Test</title>
    </head>

    <body>
	    	<div style="background-color: #FFCC66; padding : 5px;" align="center">
	      		<h1>#msg("mail.body.welcome")</h1>
	      	</div>
		
		<div align="left" style="background-color:#FFFFCC; padding-left: 20px;" >	    
	       <h2>#msg("mail.body.welcomeUser") ${username},</h2>
	       
	       <div style="padding-left: 30px;">
	       		<h3>#msg("mail.body.description")</h3>
	       	    <h3>#msg("mail.body.thankyou.1")</h3>
	       	    <h3>#msg("mail.body.thankyou.2")</h3>
	       </div>
	       <h2>#msg("mail.body.thankyou.3")</h2>
	    </div>
    </body>
  </HTML>
```

2) Inject EmailService

```
@Autowired
EmailService emailService;
```
3) Create an EmailWrapper object

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
