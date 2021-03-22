package chess.domain.command;

import java.util.List;

public class CommandTokens {
    private static final int MAIN_COMMAND_INDEX = 0;
    private static final int CURRENT_COORDINATION_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;
    private static final int SINGLE_COMMAND_TOKEN_SIZE = 1;
    private static final int MULTI_COMMAND_TOKEN_SIZE = 3;

    private final List<String> commandTokens;

    public CommandTokens(List<String> commandTokens) {
        validateCommandTokens(commandTokens);
        this.commandTokens = commandTokens;
    }

    private void validateCommandTokens(List<String> commandTokens) {
        int commandSize = commandTokens.size();
        if (commandSize != SINGLE_COMMAND_TOKEN_SIZE && commandSize != MULTI_COMMAND_TOKEN_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 명령어 토큰입니다.");
        }
    }

    public String findMainCommandToken() {
        return commandTokens.get(MAIN_COMMAND_INDEX);
    }

    public String findCurrentCoordinateToken() {
        return commandTokens.get(CURRENT_COORDINATION_INDEX);
    }

    public String findDestinationCoordinateToken() {
        return commandTokens.get(DESTINATION_COORDINATE_INDEX);
    }
}
