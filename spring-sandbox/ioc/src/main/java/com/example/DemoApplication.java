package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        DayBusPass dayBusPass1 = context.getBean(DayBusPass.class);
        DayBusPass dayBusPass2 = context.getBean(DayBusPass.class);
        System.out.println("DayBusPass 1 : " + dayBusPass1);
        System.out.println("DayBusPass 2 : " + dayBusPass2);
    }
}
