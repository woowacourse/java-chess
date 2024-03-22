package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> command.text.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public String getText() {
        return text;
    }
}
