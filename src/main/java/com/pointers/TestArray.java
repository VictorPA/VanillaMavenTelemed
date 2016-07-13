package com.pointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 08/06/2016.
 */
public class TestArray {

    static void nuller(Object[] obj){
        obj[0] = null;
    }

    public static void main(String[] args) {

        Object object = new Object();
        Object tobject[] = new Object[1];
        tobject[0] = object;

        TestArray.nuller(tobject);
        if(tobject[0] == null)
            System.out.println("L'object s'est fait nulled");



    }
}
