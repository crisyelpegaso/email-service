package com.store.services;

import java.util.Locale;

import com.store.domain.EmailWrapper;
import com.store.domain.PDF;

public interface EmailService {

    /**
     * This method send email to the email in the account object, with the attachments in the list.
     * 
     * @param email {@link Email}
     * @param pdf {@link PDF}
     * @param locale {@link Locale}
     */
    public void sendEmailWithAttachments(EmailWrapper email, PDF pdf, Locale locale);
    
    /**
     * @param email
     * @param locale
     */
    public void sendSimpleEmail(EmailWrapper email, Locale locale);
    
}
