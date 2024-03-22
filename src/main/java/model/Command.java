package model;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START("start"),
    MOVE("move"),
    POSITION("[a-h][1-8]"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        return Arrays.stream(values())
                .filter(command -> Pattern.compile(command.value).matcher(value).matches())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력하였습니다."));
    }

    public static void validate(String input) {
        boolean match = Arrays.stream(values())
                .anyMatch(command -> Pattern.compile(command.value).matcher(input).matches());
        if (!match) {
            throw new IllegalArgumentException("잘못된 명령어를 입력하였습니다.");
        }
    }
}
