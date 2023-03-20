package chess.controller;

import java.util.Arrays;


public enum Command {
    START,
    END,
    MOVE;

    public static Command createInitMessage(String command) {
        if (command.equalsIgnoreCase(START.name()) || command.equalsIgnoreCase(END.name())) {
            return from(command);
        }
        throw new IllegalArgumentException("잘못된 커맨드를 입력하였습니다.");
    }

    private static Command from(String command) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드를 입력하였습니다."));
    }

    public static Command createMoveOrEndMessage(String command) {
        if (command.equalsIgnoreCase(MOVE.name()) || command.equalsIgnoreCase(END.name())) {
            return from(command);
        }
        throw new IllegalArgumentException("잘못된 커맨드를 입력하였습니다.");
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
}
