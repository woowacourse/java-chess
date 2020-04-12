package chess.controller.command;

import chess.controller.ConsoleController;
import chess.domain.ChessManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {
    START("start", ConsoleController::start),
    END("end", ConsoleController::end),
    MOVE("move", ConsoleController::move),
    STATUS("status", ConsoleController::status);

    private final String command;
    private final BiConsumer<ChessManager, String> action;

    private static final List<Command> startCommands = new ArrayList<>(Arrays.asList(START, END));
    private static final List<Command> continueCommands = new ArrayList<>(Arrays.asList(MOVE, STATUS, END));

    Command(String command, BiConsumer<ChessManager, String> action) {
        this.command = command;
        this.action = action;
    }

    public static Command of(String inputCommand) {
        return Arrays.stream(values())
                .filter(value -> inputCommand.contains(value.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }

    public static void validateStartCommand(String inputCommand) {
        startCommands.stream()
                .map(val -> val.command)
                .filter(inputCommand::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }

    public static void validateContinueCommand(String inputCommand) {
        continueCommands.stream()
                .map(val -> val.command)
                .filter(inputCommand::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }

    public void apply(ChessManager chessManager, String input) {
        this.action.accept(chessManager, input);
    }
}
