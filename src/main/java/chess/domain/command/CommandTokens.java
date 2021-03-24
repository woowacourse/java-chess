package chess.domain.command;

import java.util.Collections;
import java.util.List;

public class CommandTokens {
    private static final int MAIN_COMMAND_INDEX = 0;
    private static final int CURRENT_COORDINATION_INDEX = 0;
    private static final int DESTINATION_COORDINATE_INDEX = 1;
    private static final int SINGLE_COMMAND_TOKEN_SIZE = 1;
    private static final int MULTI_COMMAND_TOKEN_SIZE = 3;

    private final String command;
    private final List<String> options;

    private CommandTokens(String command, List<String> options) {
        this.command = command;
        this.options = options;
    }

    public static CommandTokens from(List<String> commandTokens) {
        validateCommandTokens(commandTokens);
        String command = commandTokens.get(MAIN_COMMAND_INDEX);
        if (commandTokens.size() == SINGLE_COMMAND_TOKEN_SIZE) {
            return new CommandTokens(command, Collections.emptyList());
        }
        return new CommandTokens(command, commandTokens.subList(1, 3));
    }

    private static void validateCommandTokens(List<String> commandTokens) {
        int commandSize = commandTokens.size();
        if (commandSize != SINGLE_COMMAND_TOKEN_SIZE && commandSize != MULTI_COMMAND_TOKEN_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 명령어 토큰입니다.");
        }
    }

    public String findMainCommandToken() {
        return command;
    }

    public String findCurrentCoordinateToken() {
        return options.get(CURRENT_COORDINATION_INDEX);
    }

    public String findDestinationCoordinateToken() {
        return options.get(DESTINATION_COORDINATE_INDEX);
    }
}
