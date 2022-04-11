package chess.dto;

public class CommandDto {

    private static final int COMMAND_POSITION = 0;

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String fullCommand;
    private final String command;

    public CommandDto(String fullCommand, String command) {
        this.fullCommand = fullCommand;
        this.command = command;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public String getCommand() {
        return command;
    }

}
