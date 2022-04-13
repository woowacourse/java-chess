package chess.dto;

public class CommandDto {

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
