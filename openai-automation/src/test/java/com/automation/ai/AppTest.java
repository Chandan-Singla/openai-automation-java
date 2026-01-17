package com.automation.ai;

import com.automation.ai.service.ExampleService;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testServiceMessage() {
        ExampleService service = new ExampleService();
        Assert.assertEquals("Hello from ExampleService!", service.getMessage());
    }
}
