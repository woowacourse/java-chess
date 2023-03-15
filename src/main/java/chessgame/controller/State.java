package chessgame.controller;

import java.util.Arrays;
import java.util.Optional;

public enum State {
    START("start"),
    MOVE("move"),
    END("end");

    private final String state;

    State(String state) {
        this.state = state;
    }

    public static Optional<State> getState(String input) {
        return Arrays.stream(State.values())
            .filter(state -> state.state.equals(input))
            .findFirst();
    }
}
