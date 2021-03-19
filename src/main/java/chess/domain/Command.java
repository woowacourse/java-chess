package chess.domain;

import chess.controller.ChessController;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public enum Command {
    START("start", ChessController::start),
    MOVE("move", ChessController::move, ),
    END("end", ChessController::end);

    private final String option;
    private final Consumer<ChessController> consumer;

    Command(String option, Consumer<ChessController> consumer) {
        this.option = option;
        this.consumer = consumer;
    }

    public static void execute(String input, ChessController chessController) {
        String[] splitInput = input.split(" ");
        if (splitInput[0].equals("move")) {
            chessController.move(splitInput[1], splitInput[2]);
        }
        Command findCommand = findCommandFromInput(input.split(" ")[0]);
        findCommand.consumer.accept(chessController);
    }

    private static Command findCommandFromInput(String input) {
        return Arrays.stream(Command.values())
                .filter(command -> command.option.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
