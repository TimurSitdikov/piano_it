package com.piano.it.utils;

import java.util.List;

public class TestUtils {

    public static boolean containsCaseInsensitive(List<String> l, String s){
        for (String string : l){
            if (string.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

}
