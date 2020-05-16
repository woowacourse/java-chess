package chess.util;

public class SmallLetterAsciiConverter {
    public static final int CONVERTING_NUMBER = 96;
    private static final int TARGET_INDEX = 0;
    private static final int SMALL_A_ASCII_NUMBER = 97;
    private static final int SMALL_Z_ASCII_NUMBER = 122;

    public static int convert(String input) {
        int number = input.charAt(TARGET_INDEX);
        if (number < SMALL_A_ASCII_NUMBER || SMALL_Z_ASCII_NUMBER < number) {
            throw new IllegalArgumentException("해당 문자는 소문자가 아닙니다.");
        }
        return number - CONVERTING_NUMBER;
    }
}
