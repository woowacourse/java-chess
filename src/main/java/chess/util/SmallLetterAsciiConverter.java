package chess.util;

public class SmallLetterAsciiConverter {

    private static final int TARGET_INDEX = 0;
    private static final int CONVERTING_NUMBER = 96;

    public static int convert(String input) {
        int number = input.charAt(TARGET_INDEX);
        if (number < 97 || 122 < number) {
            throw new IllegalArgumentException("해당 문자는 소문자가 아닙니다.");
        }
        return number - CONVERTING_NUMBER;
    }
}
