package com.lambadas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 03/06/2016.
 */
public class Lambada {

    public static void main(String[] args) {
        List<String> myList = new ArrayList<String>();

        myList.add("salut");
        myList.forEach(System.out::println);
        int maVAr = 4;

        myList.forEach(e-> System.out.println(maVAr  + 3));
        JButton bouton = new JButton("Incrementer");

        int compteur = 0;
        bouton.addActionListener(event -> System.out.println(compteur));
    }

}
