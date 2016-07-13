package com.annotations;

/**
 * Created by Victor on 31/05/2016.
 */
@Infos(author = "Vic", date = "31/05/2016")

public class Grue {

    public void monter(){
        System.out.println("Je monte");
    }

    public static Grue construireGrue(MySupplier<Grue> gSupp){
        return gSupp.get();
    }

    public static void main(String[] args) {
        IGrue grue3 = () ->{};
        IGrue grue = () -> System.out.println("Je monte encore plus");



        Grue grue4 = Grue.construireGrue(Grue::new);


        grue.monter();


    }
}
