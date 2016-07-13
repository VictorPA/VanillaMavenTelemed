package com.springTraining;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Victor on 15/06/2016.
 */
public class Appli {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Lac lac = (Lac)context.getBean("lac");
        System.out.println(lac.getAlligator().toString());


        Number myNumber = null;
        Serializable mySerializable = null;
        List<? extends Number> myList = null;

    }
}
