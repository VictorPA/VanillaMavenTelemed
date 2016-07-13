package com.manuel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Victor on 30/05/2016.
 */
public class Acteur {

    private String name;


    private Speaker speaker;

    public Acteur() {
        System.out.println("L'intervenant prend la parole.");
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getName() {
        return name;
    }

    public void lecture() {
        this.speaker.lecture();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Acteur obj = (Acteur) context.getBean("acteur");
        System.out.println();
        System.out.println("Son nom est : " + obj.getName());
        obj.lecture();


    }

}
