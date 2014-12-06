package com.store.builder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.store.domain.CompositePDF;
import com.store.domain.PDF;
import com.store.domain.SimplePDF;
import com.store.exception.PDFFileCreationException;

@Component
public class PDFBuilder {

    
    public PDF buildPDF(String filename, Locale locale, String customerId){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        ByteArrayOutputStream pdfContent = new ByteArrayOutputStream();
        try {
            IOUtils.copy(inputStream, pdfContent);
        } catch (IOException e) {
            throw new PDFFileCreationException(e.getMessage());
        }
        byte [] file = pdfContent.toByteArray(); 
        return new SimplePDF(file, customerId); 
    };
    
    
    public PDF buildPDF(List<String> filenames, Locale locale, String customerId){
        CompositePDF pdf =  new CompositePDF();
        for(String filename : filenames){
            SimplePDF simplePDF = (SimplePDF) buildPDF(filename, locale, customerId);
                pdf.addPDF(simplePDF);
        }
        return pdf;
    };
}
