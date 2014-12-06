package com.store.helper;

import java.util.Locale;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.store.domain.TemplateData;

@Component
public class TemplateHelper {

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private VelocityEngine velocityEngine;

    public String merge(String templateLocation, TemplateData data, Locale locale) {

        if (data == null) {
            data = new TemplateData(this.messageSource, locale);
        }

        if (!data.contains("locale")) {
            data.add("locale", locale);
        }

        String text =
            VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine,
                templateLocation, data.getTemplateObjects());

        return text;
    }
}
