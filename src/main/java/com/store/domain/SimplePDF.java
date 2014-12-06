package com.store.domain;

import javax.mail.MessagingException;

import org.joda.time.LocalDate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SimplePDF implements PDF {
    
    private static final String FILE_EXTENSION_PDF = ".pdf";

    private byte[] attachment;
    private String customerId;

    /**
     * @param attachment
     * @param subscriptionID
     */
    public SimplePDF(byte[] attachment, String subscriptionID) {
        this.attachment = attachment;
        customerId = subscriptionID;
    }

    /**
     * @see com.store.pdf.PDF#addInto(org.springframework.mail.javamail.MimeMessageHelper)
     */
    public void addInto(MimeMessageHelper messageHelper) throws MessagingException {
        
        messageHelper.addAttachment(customerId + "_" + LocalDate.now() + FILE_EXTENSION_PDF,
                new ByteArrayResource(attachment));
    }

    public byte[] getAttachment(){
        return this.attachment;
    }

}
