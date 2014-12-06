package com.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml"
})
public class EmailServiceImplTest {

    @Autowired
    EmailServiceImpl emailService;
    
    @Test
    public void testEmailServiceConfiguration(){
        Assert.assertNotNull(emailService.getFrom());
        Assert.assertNotNull(emailService.getFromName());
    }
}
