package chess.domain.utils;

import java.util.regex.Pattern;

public class RegexUtils {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-h]");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public static boolean isValidAlphaColumn(String nameValue) {
        return NAME_PATTERN.matcher(nameValue).matches();
    }

    public static boolean isValidRowNumber(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

}

