package console.controller;

import chess.domain.board.Point;
import chess.domain.game.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum Command {

    START("start", ChessConsoleController::start),
    FINISH("end", ChessConsoleController::finish),
    MOVE("move", ChessConsoleController::move),
    STATUS("status", ChessConsoleController::status);

    private final String input;
    private final BiFunction<GameState, List<Point>, GameState> executor;

    Command(String input, BiFunction<GameState, List<Point>, GameState> executor) {
        this.input = input;
        this.executor = executor;
    }

    public static Command find(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어가 없습니다."));
    }

    public GameState execute(GameState state, List<Point> arguments) {
        return this.executor.apply(state, arguments);
    }
}
