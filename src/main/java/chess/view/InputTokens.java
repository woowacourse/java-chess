package chess.view;

import java.util.Arrays;
import java.util.List;

public class InputTokens {

    private static final int START_AND_END_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int COMMAND_TOKEN_POSITION = 0;
    public static final int SOURCE_TOKEN_POSITION = 1;
    public static final int TARGET_TOKEN_POSITION = 2;

    private final List<String> tokens;

    public InputTokens(final String commandInput) {
        List<String> tokens = Arrays.stream(commandInput.split(" ")).toList();
        validateCommandSize(tokens);
        this.tokens = tokens;
    }

    private void validateCommandSize(final List<String> splitInput) {
        if (splitInput.size() != START_AND_END_COMMAND_SIZE && splitInput.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public String getCommandToken() {
        return tokens.get(COMMAND_TOKEN_POSITION);
    }

    public String getSourceCoordinateToken() {
        return tokens.get(SOURCE_TOKEN_POSITION);
    }

    public String getTargetCoordinateToken() {
        return tokens.get(TARGET_TOKEN_POSITION);
    }
}
