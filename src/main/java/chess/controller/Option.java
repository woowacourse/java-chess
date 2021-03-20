package chess.controller;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Option {
    START("start", (chessAction, message) -> chessAction.start()),
    END("end", (chessAction, message) -> chessAction.end()),
    MOVE("move", ChessAction::move),
    STATUS("status", ((chessAction, message) -> chessAction.status()));

    private final String message;
    private final BiFunction<ChessAction, String, GameStatus> function;

    Option(String message, BiFunction<ChessAction, String, GameStatus> function) {
        this.message = message;
        this.function = function;
    }

    public static Option selectedOption(String message) {
        return Arrays.stream(values())
            .filter(value -> value.message.equals(message))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못 입력 하셨습니다."));
    }

    public GameStatus execute(ChessAction chessAction, String command) {
        return function.apply(chessAction, command);
    }
}
