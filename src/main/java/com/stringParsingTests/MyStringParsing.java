package com.stringParsingTests;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Victor on 06/07/2016.
 */
public class MyStringParsing {


    private static final String[] NOM_STRING = {"L'Or�al - US", "P.V.P. - Laboratoire SEBBIN", "F�M�RIS","victor.papakirikos@hush.com",null,"MS - B�nin / DNEHS: SI r�sultats bio"};


    public static String nameFormatting(String monString) {

        monString = monString == null ? "" : monString;
        String nouveauString = StringUtils.replaceChars(monString, "' -/\\.", null);
        nouveauString = nouveauString.toUpperCase();
        nouveauString = StringUtils.stripAccents(nouveauString);

        return nouveauString;
    }


    public static void main(String[] args) {

        System.out.println("Avec Regex :");
        System.out.println();
        for (String s : NOM_STRING) {

            String regex = ComptaStringUtils.cleanStringForMatching(s);
            System.out.println(regex);

        }



    }
}
