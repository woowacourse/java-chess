package utils;

import java.io.UnsupportedEncodingException;

public class StringBytesCalculator {
    public static int calculateBytesLength(String input){
        try {
            return input.getBytes("euc-kr").length;
        } catch (UnsupportedEncodingException exception) {
            throw new UnsupportedOperationException();
        }
    }
}
