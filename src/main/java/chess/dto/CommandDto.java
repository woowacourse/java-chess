package chess.dto;

public class CommandDto {

    private static final int COMMAND_POSITION = 0;

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String fullCommand;
    private final String command;

    public CommandDto(String fullCommand) {
        this.fullCommand = fullCommand;
        this.command = extractCommand(fullCommand);
    }

    private String extractCommand(String fullCommand) {
        return fullCommand.split(COMMAND_INPUT_DELIMITER)[COMMAND_POSITION];
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public String getCommand() {
        return command;
    }

}
