package chess.controller;

import chess.controller.state.ChessGameState;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public enum ChessExecution {
    START("start", (state, input) -> state.start()),
    MOVE("move", (state, input) -> state.move(input[1], input[2])),
    STATUS("status", (state, input) -> state.status()),
    END("end", (state, input) -> state.end()),
    ;

    private static final String NO_COMMAND_FIND = "게임 실행중 명령어는 end만 입력할 수 있습니다.";

    private final String value;
    private final BiFunction<ChessGameState, String[], ChessGameState> runnable;

    ChessExecution(String value, BiFunction<ChessGameState, String[], ChessGameState> runnable) {
        this.value = value;
        this.runnable = runnable;
    }

    public static ChessExecution from(String value) {
        return Arrays.stream(values())
                .filter(execution -> execution.value.equalsIgnoreCase(value.trim()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_COMMAND_FIND));
    }

    public ChessGameState run(ChessGameState chessGameState, String[] input) {
        return runnable.apply(chessGameState, input);
    }
}
