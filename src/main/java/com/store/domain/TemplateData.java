package com.store.domain;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;

import com.google.common.collect.Maps;

public class TemplateData {

    private Map<String, Object> templateObjects = Maps.newHashMap();
    
    private MessageSource messages;
    
    private Locale locale;
    
    public TemplateData(MessageSource messageSource, Locale locale){
        this.messages = messageSource;
        this.locale = locale;
        this.add("messages", messages);
        this.add("locale", locale);
    }
    
    public Object get(String attributeName){
        return templateObjects.get(attributeName);
    }
    
    public boolean contains(String attributeName){
        return templateObjects.containsKey(attributeName);
    }
    
    public void add(String attributeName, Object attribute){
        templateObjects.put(attributeName, attribute);
    }


    public Map<String, Object> getTemplateObjects() {
        return templateObjects;
    }


    public void setTemplateObjects(Map<String, Object> templateObjects) {
        this.templateObjects = templateObjects;
    }


    public MessageSource getMessageSource() {
        return messages;
    }


    public void setMessageSource(MessageSource messageSource) {
        this.messages = messageSource;
    }


    public Locale getLocale() {
        return locale;
    }


    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    
}

