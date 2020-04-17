package chess.domain;

import java.util.Arrays;

public enum State {
    BEFORE_START(""),
    RUNNING("start"),
    FINISHED("end");

    private final String command;

    State(String command) {
        this.command = command;
    }

    public static State of(String decision) {
        return Arrays.stream(State.values())
            .filter(state -> state.command.equals(decision))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }
}
