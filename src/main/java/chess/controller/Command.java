package chess.controller;

import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {
    START("^start$", ChessController::initBoard),
    MOVE("^move\\s[a-h][1-8]\\s[a-h][1-8]$", ChessController::move),
    STATUS("^status$", ChessController::showStatus),
    END("^end$", ChessController::end);

    private static final String NOT_EXIST_OPTION = "[ERROR] 잘 못된 명령어입니다.";

    private final String command;
    BiConsumer<ChessGame, String> function;

    Command(String command, BiConsumer<ChessGame, String> function) {
        this.command = command;
        this.function = function;
    }

    private void play(ChessGame game, String command) {
        function.accept(game, command);
    }

    public static void playCommand(ChessGame game, String input) {
        Command command = Arrays.stream(Command.values())
            .filter(option -> input.matches(option.command))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_OPTION));
        command.play(game, input);
    }

}
