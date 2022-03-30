package chess.view;

import java.util.Arrays;

public enum InputOption {
    START("^start$"),
    MOVE("^move\\s[a-h][1-8]\\s[a-h][1-8]$"),
    STATUS("^status$"),
    END("^end$");

    private static final String NOT_EXIST_OPTION = "[ERROR] 잘 못된 명령어입니다.";
    private final String regex;

    InputOption(String regex) {
        this.regex = regex;
    }

    public static InputOption from(String input) {
        return Arrays.stream(InputOption.values())
            .filter(inputOption -> input.matches(inputOption.regex))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_OPTION));
    }
}
