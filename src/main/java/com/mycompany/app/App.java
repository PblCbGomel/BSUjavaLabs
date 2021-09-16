package com.mycompany.app;

public class App
{
    public static String compressString(String str) {
        String result = "";
        int symbolCount = 0;
        int len = str.length();
        char symbol = ' ';

        for(int i = 0; i < len; i++) {
            if(i == 0 || symbolCount == 0) {
                symbol = str.charAt(i);
                symbolCount = 1;
            } else if(symbol == str.charAt(i)) {
                symbolCount++;
            } else if(symbolCount == 1){
                result += Character.toString(symbol);
                symbolCount = 0;
                i--;
            } else {
                result += Character.toString(symbol) + Integer.toString(symbolCount);
                symbolCount = 0;
                i--;
            }
        }
        if(symbolCount == 1){
            result += Character.toString(symbol);
        } else if (symbolCount > 0) {
            result += Character.toString(symbol) + Integer.toString(symbolCount);
        }
        return result;
    }
}

