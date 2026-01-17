package com.automation.ai;

import com.automation.ai.service.ExampleService;

public class App {
    public static void main(String[] args) {
        ExampleService service = new ExampleService();
        System.out.println(service.getMessage());
    }
}
