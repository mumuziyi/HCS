package org.example;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,Keys> keyLocation = RequiredDataUtils.getKeysLocation();

        for (String str : keyLocation.keySet()){
            System.out.println(keyLocation.get(str).toString());
        }
    }
}
