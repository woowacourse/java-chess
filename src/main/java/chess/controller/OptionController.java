package chess.controller;

import java.util.Arrays;
import java.util.function.BiConsumer;

import chess.domain.game.ChessGame;

public enum OptionController {

    START("^start$", ChessController::start),
    END("^end$", ChessController::end),
    MOVE("^move\\s[a-h][1-8]\\s[a-h][1-8]$", ChessController::move),
    STATUS("^status$", ChessController::status);

    private static final String NOT_EXIST_OPTION = "[ERROR] 지원하지 않는 옵션입니다";

    private final String regex;
    private final BiConsumer<ChessGame, String> controllerFunction;

    OptionController(final String regex, final BiConsumer<ChessGame, String> controllerFunction) {
        this.regex = regex;
        this.controllerFunction = controllerFunction;
    }

    public static void run(ChessGame chessGame, String input) {
        Arrays.stream(OptionController.values())
            .filter(inputOption -> input.matches(inputOption.regex))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_OPTION))
            .controllerFunction
            .accept(chessGame, input);
    }
}

