package com.store.builder;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.store.base.BaseTest;
import com.store.domain.PDF;

public class PDFBuilderTest extends BaseTest {

    @Autowired
    PDFBuilder pdfBuilder;
    
    @Test
    public void testBuildPDF(){
        Locale locale = new Locale("es");
        
        PDF pdf = pdfBuilder.buildPDF(PDF_TEST_FILE, locale, CUSTOMER_ID);
        
        Assert.assertNotNull(pdf);
        
    }
    
}
