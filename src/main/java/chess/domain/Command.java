package chess.domain;

import chess.state.State;
import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ;

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command of(final String command) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }

    public State run(final State state, final String[] commands) {
        if (this == START) {
            return state.start();
        }
        if (this == MOVE) {
            return state.move(commands);
        }
        if (this == END) {
            return state.end();
        }
        if (this == STATUS) {
            return state.status();
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다.");
    }
}
