package com.store.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.store.domain.EmailWrapper;
import com.store.domain.PDF;
import com.store.domain.TemplateData;
import com.store.helper.TemplateHelper;
import com.store.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final static String EMAIL_TEMPLATES_EXTENSION = ".vm";
    
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateHelper templateHelper;
    
    @Value("${emailService.from}")
    private String from;
    
    @Value("${emailService.fromName}")
    private String fromName;

    /**
     * @see com.store.service.EmailService#sendEmailWithAttachments(com.store.domain.Email,
     *      com.store.domain.pdf.PDF, java.util.Locale)
     */
    public void sendEmailWithAttachments(final EmailWrapper email, final PDF pdf, Locale locale) {
        // Create content of mail, based on Velocity template
        final String emailContent = loadContentTemplate(email.getTemplateData(), email.getTemplateName(), locale);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom(from, fromName);
                if (email.getBcc() != null) {
                    messageHelper.setBcc(email.getBcc());
                }
                messageHelper.setTo(email.getTo());
                messageHelper.setSubject(email.getSubject());
                messageHelper.setText(emailContent, true);

                pdf.addInto(messageHelper);
            }
        };

        mailSender.send(preparator);
    }

    /**
     * Sends a simple email with velocity added
     *
     * @see com.store.service.EmailService#sendSimpleEmail(com.store.domain.Email,
     *      java.util.Locale)
     */
    public void sendSimpleEmail(final EmailWrapper email, final Locale locale) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom(from, fromName);
                messageHelper.setTo(email.getTo());
                messageHelper.setSubject(email.getSubject());
                String emailContent = "";
                if (email.getTemplateName() != null) {
                    emailContent = loadContentTemplate(email.getTemplateData(), email.getTemplateName(), locale);
                }
                messageHelper.setText(emailContent, true);

            }
        };

        mailSender.send(preparator); 
    }

    private String loadContentTemplate(TemplateData data, String templateLocation, Locale locale) {
        return templateHelper.merge(templateLocation + EMAIL_TEMPLATES_EXTENSION, data, locale);
    }

    /**
     * @param mailSender {@link JavaMailSender}
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * @param fromName
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     */
    public String getFromName() {
        return this.fromName;
    }
    
    /**
     * @param templateHelper the templateHelper to set
     */
    public void setTemplateHelper(TemplateHelper templateHelper) {
        this.templateHelper = templateHelper;
    }

}
