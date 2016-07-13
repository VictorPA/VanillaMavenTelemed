package com.stratic;

/**
 * Created by Victor on 08/06/2016.
 */
public class Bocal {


    public static Thing thing = Thing.thing;
    private String string = "salut";
    public String publicString = "salutP";

    public static String staticpublicString = "ere";


    public String getString() {
        return this.string;
    }


    public class InnerBocal {
        void test() {
            string = "te";

        }
    }

    public static class SInnerBocal {
        void test() {

            staticpublicString = "o";

        }
    }

    private class PInnerBocal {
        void test() {
        }
    }

    private static class SPInnerBocal {
        void test() {
        }
    }


    public static void main(String[] args) {
        Thing thing = new Thing();
        Thing thing2 = new Thing();


    }

}
