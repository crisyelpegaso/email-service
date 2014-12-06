package com.store.domain;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.MimeMessageHelper;

public interface PDF {
    
    /**
     * @param messageHelper
     * @throws MessagingException
     */
    public void addInto(MimeMessageHelper messageHelper) throws MessagingException;
    
}
