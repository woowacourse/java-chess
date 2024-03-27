package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command getStartCommand(String command) {
        if (!START.command.equals(command)) {
            throw new IllegalArgumentException("첫 명령어는 start만 입력할 수 있습니다.");
        }
        return START;
    }

    public static Command getProcessCommand(String command) {
        Command inputCommand = Arrays.stream(values())
                .filter(value -> value.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));

        if (inputCommand == START) {
            throw new IllegalArgumentException("게임 중에 start 명령어는 사용할 수 없습니다.");
        }

        return inputCommand;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isEnd() {
        return this == END;
    }
}
