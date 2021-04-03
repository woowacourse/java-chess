package chess.controller.console.dto;

public class CommandRequestDTO {
    private final String commandInput;
    private final String startPositionInput;
    private final String destinationInput;

    public CommandRequestDTO(String commandInput, String startPositionInput, String destinationInput) {
        this.commandInput = commandInput;
        this.startPositionInput = startPositionInput;
        this.destinationInput = destinationInput;
    }

    public CommandRequestDTO(String commandInput) {
        this(commandInput, null, null);
    }

    public String getCommandInput() {
        return commandInput;
    }

    public String getStartPositionInput() {
        return startPositionInput;
    }

    public String getDestinationInput() {
        return destinationInput;
    }
}
