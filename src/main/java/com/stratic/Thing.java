package com.stratic;

/**
 * Created by Victor on 08/06/2016.
 */
public class Thing {

    private Thing mySelf;
    public Thing(){
        mySelf = this;
    }

    public static Thing thing = new Thing();
}
