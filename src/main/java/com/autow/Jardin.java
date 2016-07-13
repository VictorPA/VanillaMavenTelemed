package com.autow;

import com.config.SpringConfiguration;
import com.manuel.Acteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Victor on 30/05/2016.
 */

public class Jardin {
    @Autowired
    Arbre arbre;

    public Arbre getArbre() {
        return arbre;
    }

    public static void main(String[] args) {
        System.out.println("-----------------------");
        ConfigurableApplicationContext contextAuto = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Jardin jardin = (Jardin)contextAuto.getBean("jardin");
        System.out.println(jardin.getArbre().toString());
        contextAuto.start();
    }
}
