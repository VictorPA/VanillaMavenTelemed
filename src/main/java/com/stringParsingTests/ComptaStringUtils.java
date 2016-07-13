package com.stringParsingTests;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Victor on 06/07/2016.
 */
public class ComptaStringUtils extends StringUtils {
    /**
     * Removes accents, converts to uppercase and removes all non alphanumeric characters
     *<p>If the passed parameter is <null>null</null>, it is replaced by an empty string.</p>
     * @param s The string to be cleaned
     * @return The cleaned string
     * <p>Ex : "L'Oréal - US" will become "LOREALUS"</p>
     */
    public static String cleanStringForMatching(String s) {
        s = s == null ? "" : s;
        s = StringUtils.stripAccents(s);
        s = s.toUpperCase();
        s = s.replaceAll("[^\\p{Alpha}]", "");
        return s;

    }

}

