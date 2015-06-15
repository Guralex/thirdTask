package com.epam.gura.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HelperUtils {
   

    public static String nameOfProduct(String name) {
        Pattern pattern = Pattern.compile("([^/]+)");
        Matcher matcher = pattern.matcher(name);
        String correctName = "";
        while (matcher.find()) {
            correctName = matcher.group(1).replace("&amp;", "&").trim();
            break;
        }
        return correctName;
    }


}
