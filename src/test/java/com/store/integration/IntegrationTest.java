package com.store.integration;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.store.domain.EmailWrapper;
import com.store.domain.TemplateData;
import com.store.services.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"
})
public class IntegrationTest {

    @Autowired
    EmailService emailService;
 
    @Autowired
    MessageSource messageSource;
    
    @Autowired
    private ApplicationContext appContext;

    @Test
    public void testSendEmailEnglish(){
        Locale locale = new Locale("en");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", "cris");
        templateData.add("urlInfo", "http://github.com");
        
        EmailWrapper email = new EmailWrapper("rodriguezmacristina@gmail.com", "test ", "test_template", templateData);
        
        
        emailService.sendSimpleEmail(email, locale);
    }
    
    @Test
    public void testSendEmailSpanish(){
        Locale locale = new Locale("es");
        TemplateData templateData = new TemplateData(messageSource, locale);
        templateData.add("username", "cris");
        
        EmailWrapper email = new EmailWrapper("rodriguezmacristina@gmail.com", "test ", "test_template", templateData);
        
        
        emailService.sendSimpleEmail(email, locale);
    }
}
