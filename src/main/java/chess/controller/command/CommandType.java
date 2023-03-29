package chess.controller.command;

import chess.controller.command.exception.CommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// TODO: 상태로 묶기
public enum CommandType {

    START("start", 1),
    END("end", 1),
    MOVE("move", 3),
    STATUS("status", 1),
    LOAD("load", 2),
    SAVE("save", 1);

    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_DESTINATION_INDEX = 2;
    private static final int COMMAND_KEYWORD_INDEX = 0;

    private final String keyword;
    private final int commandSize;

    CommandType(final String keyword, final int commandSize) {
        this.keyword = keyword;
        this.commandSize = commandSize;
    }

    public static CommandType from(final List<String> input) {
        return Arrays.stream(values())
                .filter(commandType -> commandType.isCorrectCommand(input))
                .findFirst()
                .orElseThrow(CommandException::new);
    }

    private boolean isCorrectCommand(final List<String> input) {
        return isSameKeyword(input) && isCorrectCommandSize(input);
    }

    private boolean isSameKeyword(final List<String> input) {
        return Objects.equals(keyword, input.get(COMMAND_KEYWORD_INDEX));
    }

    private boolean isCorrectCommandSize(final List<String> input) {
        return commandSize == input.size();
    }
}
