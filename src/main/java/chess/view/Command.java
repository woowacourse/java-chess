package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ;

    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.isTextEqualsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    private boolean isTextEqualsIgnoreCase(String input) {
        return text.equalsIgnoreCase(input);
    }

    public String getText() {
        return text;
    }
}
