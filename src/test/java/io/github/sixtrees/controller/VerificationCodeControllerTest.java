package io.github.sixtrees.controller;

import io.github.sixtrees.JunitTestParent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class VerificationCodeControllerTest extends JunitTestParent {
    @Autowired
    VerificationCodeController verificationCodeController;

    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        session = new MockHttpSession();
    }

    @Test
    public void loginCode() throws Exception {
        verificationCodeController.loginCode(response, session);
    }

}