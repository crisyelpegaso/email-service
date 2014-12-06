package com.store.integration;

import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.store.base.BaseTest;
import com.store.builder.PDFBuilder;
import com.store.domain.EmailWrapper;
import com.store.domain.PDF;
import com.store.domain.TemplateData;
import com.store.services.EmailService;

public class IntegrationTest extends BaseTest {

    @Autowired
    EmailService emailService;
 
    @Autowired
    MessageSource messageSource;
    
    @Autowired
    PDFBuilder pdfBuilder;
    
    @Value("${test.to}")
    private String to;
    
    @Value("${test.subject}")
    private String subject;
    
    @Value("${test.templatename}")
    private String templatename;
    
    @Test
    public void testSendEmailEnglish(){
        Locale locale = new Locale("en");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", USERNAME);
        templateData.add("urlInfo", URL_REPO);
        
        EmailWrapper email = new EmailWrapper(to, subject, templatename, templateData);
        emailService.sendSimpleEmail(email, locale);
    }
    
    @Test
    public void testSendEmailSpanish(){
        Locale locale = new Locale("es");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", USERNAME);
        templateData.add("urlInfo", URL_REPO);
        
        EmailWrapper email = new EmailWrapper(to, subject, templatename, templateData);
        emailService.sendSimpleEmail(email, locale);
    }
    
    @Test
    public void testSendEmailSpanishWithAttachment(){
        Locale locale = new Locale("es");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", USERNAME);
        
        PDF pdf = pdfBuilder.buildPDF(PDF_TEST_FILE, locale, CUSTOMER_ID);
        EmailWrapper email = new EmailWrapper(to, subject, templatename, templateData);
        emailService.sendEmailWithAttachments(email, pdf, locale);
    }
    
    @Test
    public void testSendEmailSpanishWithAttachments(){
        Locale locale = new Locale("es");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", USERNAME);
        
        List<String> attachments = Lists.newArrayList();
        attachments.add(PDF_TEST_FILE);
        attachments.add(PDF_TEST_FILE);
        PDF pdf = pdfBuilder.buildPDF(attachments, locale, "customer_id");
        EmailWrapper email = new EmailWrapper(to, subject, templatename, templateData);
        emailService.sendEmailWithAttachments(email, pdf, locale);
    }
    
}
