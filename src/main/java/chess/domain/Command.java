package chess.domain;

import chess.controller.ChessController;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {
    START("start", ChessController::start),
    MOVE("move", ChessController::move),
    END("end", ChessController::end);

    private final String command;
    BiConsumer<Game, String> function;

    Command(String command, BiConsumer<Game, String> function) {
        this.command = command;
        this.function = function;
    }

    public static void playCommand(Game game, String input) {
        String processedInput = input.split(" ")[0];
        Command command = Arrays.stream(values())
            .filter(element -> element.command.equals(processedInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 메세지입니다."));
        command.play(game, input);
    }

    private void play(Game game, String command) {
        function.accept(game, command);
    }
}
