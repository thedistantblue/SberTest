package com.sberbank.testapp;

import com.sberbank.starter.ApplicationStarter;
import com.sberbank.testapp.configuration.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMainClass {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ApplicationStarter starter =
                context.getBean(ApplicationStarter.class);
        starter.start();
    }
}
