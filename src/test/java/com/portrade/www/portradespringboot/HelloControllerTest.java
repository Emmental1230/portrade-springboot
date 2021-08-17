package com.portrade.www.portradespringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return(){
        String hello = "hello";

    }

}
