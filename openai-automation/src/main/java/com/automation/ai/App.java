package com.automation.ai;
//import com.automation.ai.service.ExampleService;

import com.automation.ai.config.AppConfig;

// public class App {
//     public static void main(String[] args) {
//         ExampleService service = new ExampleService();
//         System.out.println(service.getMessage());
//     }
// }


public class App {
    public static void main(String[] args) {
        System.out.println("App Name   : " + AppConfig.get("app.name"));
        System.out.println("Version    : " + AppConfig.get("app.version"));
        System.out.println("Environment: " + AppConfig.get("app.env"));
    }
}


