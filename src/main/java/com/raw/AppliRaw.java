package com.raw;

/**
 * Created by Victor on 08/06/2016.
 */
public class AppliRaw {
    public static void main(String[] args) {

        MonRaw<Number> monRaw = new MonRaw<>(new MonT<>());

        monRaw.getMonT().setT(38);

    }
}
