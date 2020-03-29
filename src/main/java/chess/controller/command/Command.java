package chess.controller.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String inputCommand) {
        return Arrays.stream(values())
                .filter(value -> inputCommand.contains(value.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }

    public static void validateStartCommand(String inputCommand) {
        List<Command> startCommands = new ArrayList<>(Arrays.asList(START, END));

        startCommands.stream()
                .map(val -> val.command)
                .filter(inputCommand::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }

    public static void validateContinueCommand(String inputCommand) {
        List<Command> continueCommands = new ArrayList<>(Arrays.asList(MOVE, STATUS, END));

        continueCommands.stream()
                .map(val -> val.command)
                .filter(inputCommand::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 잘못된 명령어입니다.", inputCommand)));
    }
}
