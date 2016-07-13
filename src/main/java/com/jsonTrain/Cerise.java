package com.jsonTrain;

/**
 * @author Victor Papakirikos (vpa)
 * @since 11/07/2016
 */
public class Cerise {

    private double taille;
    private int graines;
    private String nom;
    private boolean estMangeable;

    @Override public String toString() {
        return "Cerise{" +
                "taille=" + taille +
                ", graines=" + graines +
                ", nom='" + nom + '\'' +
                ", estMangeable=" + estMangeable +
                '}';
    }

    public double getTaille() {
        return taille;
    }

    public int getGraines() {
        return graines;
    }

    public String getNom() {
        return nom;
    }

    public boolean isEstMangeable() {
        return estMangeable;
    }
}
