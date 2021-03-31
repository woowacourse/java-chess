package chess.controller.command;

import chess.controller.ChessController;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum CommandMenu {
    START("start", Start::of),
    RESTART("restart", Restart::of),
    END("end", End::of),
    MOVE("move", Move::of),
    STATUS("status", Status::of),
    SHOW("show", Show::of);

    private static final int COMMAND_INDEX = 0;

    private final String command;
    private final BiFunction<ChessController, List<String>, Command> biFunction;

    CommandMenu(final String command, final BiFunction<ChessController, List<String>, Command> biFunction) {
        this.command = command;
        this.biFunction = biFunction;
    }

    public static Command findCommandByInputCommand(final ChessController chessController, final List<String> inputCommand) {
        return Arrays.stream(values())
                .filter(commandMenu -> commandMenu.command.equalsIgnoreCase(inputCommand.get(COMMAND_INDEX)))
                .map(commandMenu -> commandMenu.biFunction.apply(chessController, inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어입니다."));
    }
}
