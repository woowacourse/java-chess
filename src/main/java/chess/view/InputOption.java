package chess.view;

import java.util.Arrays;

public enum InputOption {

    START("^start$"),
    END("^end$"),
    MOVE("^move\\s[a-h][1-8]\\s[a-h][1-8]$");

    private static final String NOT_EXIST_OPTION = "[ERROR] 지원하지 않는 옵션입니다";

    private final String regex;

    InputOption(final String regex) {
        this.regex = regex;
    }

    public static String from(String input) {
        if(!isExistOption(input)) {
            throw new IllegalArgumentException(NOT_EXIST_OPTION);
        }
        return input;
    }

    private static boolean isExistOption(String input) {
        return Arrays.stream(InputOption.values())
            .anyMatch(inputOption -> input.matches(inputOption.regex));
    }
}

