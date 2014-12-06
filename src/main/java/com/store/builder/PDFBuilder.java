package com.store.builder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.store.domain.CompositePDF;
import com.store.domain.PDF;
import com.store.domain.SimplePDF;
import com.store.exception.PDFFileCreationException;

public class PDFBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFBuilder.class);
    
    public PDF buildPDF(String filename, Locale locale, String customerId){
        File file = new File(filename);
        try {
            byte[] fileByteArray = Files.toByteArray(file);
            return new SimplePDF(fileByteArray, customerId);
        } catch (IOException e) {
            LOGGER.error("Building byte array from file failed");
            throw new PDFFileCreationException();
        } 
    };
    
    public PDF buildPDF(List<String> filenames, Locale locale, String customerId){
        CompositePDF pdf =  new CompositePDF();
        for(String filename : filenames){
            File file = new File(filename);
            try {
                byte[] fileByteArray = Files.toByteArray(file);
                pdf.addPDF(new SimplePDF(fileByteArray, customerId));
            } catch (IOException e) {
                LOGGER.error("Building byte array from file failed");
                throw new PDFFileCreationException();
            }    
        }
        return pdf;
    };
}
