package com.healthmate.service;

import com.healthmate.service.dependency.ServiceComponent;
import org.springframework.boot.SpringApplication;
import com.healthmate.service.dependency.DaggerServiceComponent;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static final ServiceComponent serviceComponent = DaggerServiceComponent.create();
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);


    }
}
