package com.store.domain;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.google.common.collect.Lists;

public class CompositePDF implements PDF{

    private List<PDF> composite = Lists.newArrayList();

    /**
     * @see com.store.pdf.PDF#addInto(org.springframework.mail.javamail.MimeMessageHelper)
     */
    public void addInto(MimeMessageHelper messageHelper) throws MessagingException {
        for (PDF pdf : composite) {
            pdf.addInto(messageHelper);
        }
    }

    /**
     * @param pdf {@link PDF}
     */
    public void addPDF(PDF pdf) {
        composite.add(pdf);
    }

}
