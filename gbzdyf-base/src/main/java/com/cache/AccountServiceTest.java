package com.cache;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by y on 2016/12/15.
 */
public class AccountServiceTest {

    private AccountService accountService;

    private final Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        accountService = context.getBean("accountService", AccountService.class);
    }

    @Test
    public void testInject(){
        assertNotNull(accountService);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");

        accountService.reload();
        logger.info("after reload ....");

        accountService.getAccountByName("accountName");
        accountService.getAccountByName("accountName");
    }
}
