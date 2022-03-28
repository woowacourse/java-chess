package chess.controller;

import java.util.Arrays;

public enum Command {

    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String[] gameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(gameCommand[0]))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어를 입력하셨습니다."));
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
