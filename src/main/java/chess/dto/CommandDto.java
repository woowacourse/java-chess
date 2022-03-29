package chess.dto;

import chess.domain.command.Command;

public class CommandDto {

    private static final int COMMAND_POSITION = 0;

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String fullCommand;
    private final Command command;

    public CommandDto(String fullCommand) {
        this.fullCommand = fullCommand;
        this.command = Command.of(extractCommand(fullCommand));
    }

    private String extractCommand(String fullCommand) {
        return fullCommand.split(COMMAND_INPUT_DELIMITER)[COMMAND_POSITION];
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public Command getCommand() {
        return command;
    }

}
