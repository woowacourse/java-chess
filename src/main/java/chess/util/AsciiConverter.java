package chess.util;

public class AsciiConverter {
    public static final int SMALL_LETTER_CONVERTING_NUMBER = 96;
    private static final int TARGET_INDEX = 0;
    private static final int SMALL_A_ASCII_NUMBER = 97;
    private static final int SMALL_Z_ASCII_NUMBER = 122;
    private static final int NATURAL_NUMBER_ONE_ASCII_NUMBER = 49;
    private static final int NATURAL_NUMBER_NINE_ASCII_NUMBER = 57;


    public static int convert(String input) {
        int number = input.charAt(TARGET_INDEX);
        if (SMALL_A_ASCII_NUMBER <= number && number <= SMALL_Z_ASCII_NUMBER) {
            return number - SMALL_LETTER_CONVERTING_NUMBER;

        }

        if (NATURAL_NUMBER_ONE_ASCII_NUMBER <= number && number <= NATURAL_NUMBER_NINE_ASCII_NUMBER) {
            return Integer.valueOf(input);
        }

        throw new IllegalArgumentException("해당 문자는 소문자가 아닙니다.");

    }
}
