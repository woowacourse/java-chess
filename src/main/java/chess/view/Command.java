package chess.view;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public static void validate(String input) {
        Arrays.stream(values())
                .filter(it -> input.startsWith(it.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public static Command splitCommand(String text) {
        String[] splitText = text.split(" ");
        return Arrays.stream(values())
                .filter(it -> it.value.equals(splitText[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public static String getFromPosition(String text) {
        return text.split(" ")[1];
    }

    public static String getToPosition(String text) {
        return text.split(" ")[2];
    }
}
