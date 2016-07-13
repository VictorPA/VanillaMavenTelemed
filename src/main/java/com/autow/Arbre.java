package com.autow;

import org.springframework.stereotype.Component;

/**
 * Created by Victor on 30/05/2016.
 */
@Component
public class Arbre {

    int taille;

    public Arbre(int taille) {
        this.taille = taille;
    }

    public String toString(){
        return "L'arbre est bien là";
    }
}
