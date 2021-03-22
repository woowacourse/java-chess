package chess.domain.state.command;

import chess.domain.state.exception.CommandNotFoundException;
import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private static final String BLANK = " ";
    private static final int MOVE_COMMAND_WORDS = 3;
    private static final int COMMON_COMMAND_WORDS = 1;

    private final String description;

    CommandType(String description) {
        this.description = description;
    }

    public static CommandType from(String command) {
        validate(command);
        String kind = command.split(BLANK)[0];

        return Arrays.stream(values())
            .filter(commandType -> commandType.description.equals(kind))
            .findFirst()
            .orElseThrow(() -> new CommandNotFoundException(command));
    }

    private static void validate(String command) {
        String[] words = command.split(BLANK);
        String commandType = words[0];
        if (commandType.equals(MOVE.description) && words.length != MOVE_COMMAND_WORDS) {
            throw new CommandNotFoundException(command);
        }
        if (!commandType.equals(MOVE.description) && words.length != COMMON_COMMAND_WORDS) {
            throw new CommandNotFoundException(command);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
