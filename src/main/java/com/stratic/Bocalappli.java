package com.stratic;

/**
 * Created by Victor on 08/06/2016.
 */
public class Bocalappli {


    public static void main(String[] args) {
        Bocal bocal = new Bocal();
        Bocal.InnerBocal ib = new Bocal().new InnerBocal();
        Bocal.SInnerBocal sib = new Bocal.SInnerBocal();

        bocal.publicString = "Z";




        System.out.println();
    }
}
