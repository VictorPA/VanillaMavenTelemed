package com.monBus;


import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created by Victor on 02/06/2016.
 */
public class Que {
    public Que() {
        JFrame jframe = new JFrame();
        Container jP = jframe.getContentPane();
        JButton jButton = new JButton();
        jP.add(jButton);
        /*
        jButton.addActionListener(e -> System.out.println("salut"));
        jButton.addActionListener(e -> JOptionPane.showMessageDialog(null,"Salut"));
        */

        jframe.setLocationRelativeTo(null);

        jP.setLayout(new FlowLayout());
        jframe.setVisible(true);
        jframe.pack();
        jframe.setSize(new Dimension(400,400));
        jP.setPreferredSize(new Dimension(200,200));
        jButton.setPreferredSize(new Dimension(100,100));
        jButton.setText("Salut");
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        Que que = new Que();

    }


}
