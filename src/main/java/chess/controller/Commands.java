package chess.controller;

import chess.domain.Game;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Commands {
    START("start", ChessController::start),
    MOVE("move", ChessController::move),
    STATUS("status", ChessController::status),
    END("end", ChessController::end);

    public static final String WRONG_COMMAND = "잘못된 메세지입니다.";

    private final String command;
    BiConsumer<Game, String> function;

    Commands(final String command, final BiConsumer<Game, String> function) {
        this.command = command;
        this.function = function;
    }

    public static void playCommand(final Game game, final String input) {
        String processedInput = input.split(" ")[0];
        Commands commands = Arrays.stream(values())
            .filter(element -> element.command.equals(processedInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND));
        commands.play(game, input);
    }

    private void play(final Game game, final String command) {
        function.accept(game, command);
    }
}
