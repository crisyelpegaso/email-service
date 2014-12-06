package com.store.domain;


public class EmailWrapper {

    private String to;
    private String subject;
    private String templateName;
    private TemplateData templateData;
    private String bcc;
    
    /**
     * @param to
     * @param subject
     * @param templateName
     * @param templateData
     */
    public EmailWrapper(String to, String subject, String templateName, TemplateData templateData) {
        this.to = to;
        this.subject = subject;
        this.templateName = templateName;
        this.templateData = templateData;
    }
    
    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }
    
    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * @return the templateData
     */
    public TemplateData getTemplateData() {
        return templateData;
    }
    
    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }
    
    /**
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }
    
    /**
     * @param bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    

}
