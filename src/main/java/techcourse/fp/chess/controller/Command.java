package techcourse.fp.chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
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

    public static Command createInitMessage(String rawCommand) {
        if (rawCommand.equals(START.command) || rawCommand.equals(END.command)) {
            return from(rawCommand);
        }

        throw new IllegalArgumentException("잘못된 커맨드를 입력하였습니다.");
    }

    private static Command from(String rawCommand) {
        return Arrays.stream(values())
                .filter(command -> command.command.equals(rawCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드를 입력하였습니다."));
    }

    public static Command createMoveOrEndMessage(String rawCommand) {
        if (rawCommand.equals(MOVE.command) || rawCommand.equals(END.command)) {
            return from(rawCommand);
        }

        throw new IllegalArgumentException("잘못된 커맨드를 입력하였습니다.");
    }
}
