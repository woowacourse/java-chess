package chess.domain;

import chess.state.State;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public enum ChessExecution {
    START("start", (state, input) -> state.start()),
    MOVE("move", (state, input) -> state.move(input)),
    STATUS("status", (state, input) -> state.status()),
    END("end", (state, input) -> state.end()),
    ;

    private static final String NO_COMMAND_FIND = "게임 실행중 명령어는 end만 입력할 수 있습니다.";

    private final String value;
    private final BiFunction<State, String, State> runnable;

    ChessExecution(String value, BiFunction<State, String, State> runnable) {
        this.value = value;
        this.runnable = runnable;
    }

    public static ChessExecution from(String value) {
        return Arrays.stream(values())
                .filter(execution -> execution.value.equalsIgnoreCase(value.trim()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_COMMAND_FIND));
    }

    public State run(State state, String input) {
        return runnable.apply(state, input);
    }
}
