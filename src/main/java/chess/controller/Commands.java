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

    Commands(String command, BiConsumer<Game, String> function) {
        this.command = command;
        this.function = function;
    }

    public static void playCommand(Game game, String input) {
        String processedInput = input.split(" ")[0];
        Commands commands = Arrays.stream(values())
            .filter(element -> element.command.equals(processedInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND));
        commands.play(game, input);
    }

    private void play(Game game, String command) {
        function.accept(game, command);
    }
}
