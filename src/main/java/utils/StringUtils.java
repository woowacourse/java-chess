package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String DELIMITER = " ";

    private StringUtils() {
    }

    public static List<String> splitIntoList(final String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new NullPointerException("사용자 입력이 잘못되었습니다.");
        }
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
