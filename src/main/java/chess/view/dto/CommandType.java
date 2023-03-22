package chess.view.dto;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;
import java.util.Set;

public enum CommandType {

    START, END, MOVE;
    
    private static final Set<CommandType> SINGLE_COMMAND_TYPES = Set.of(START, END);

    public static CommandType from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }

    public boolean isSingleCommandType() {
        return SINGLE_COMMAND_TYPES.contains(this);
    }
}
