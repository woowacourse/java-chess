package chess.dto;

public class CommandDto {

    private static final int COMMAND_POSITION = 0;

    private static final String COMMAND_INPUT_DELIMITER = " ";

    private final String command;
    private final String gameState;

    public CommandDto(String command) {
        this.command = command;
        this.gameState = command.split(COMMAND_INPUT_DELIMITER)[COMMAND_POSITION];
    }

    public String getCommand() {
        return command;
    }

    public String getGameState() {
        return gameState;
    }

}
