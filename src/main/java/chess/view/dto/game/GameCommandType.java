package chess.view.dto.game;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Set;

public enum GameCommandType {

    START, END, MOVE, STATUS;

    private static final Set<GameCommandType> SINGLE_COMMAND_TYPES = Set.of(START, END, STATUS);

    public static GameCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    public boolean isSingleCommandType() {
        return SINGLE_COMMAND_TYPES.contains(this);
    }
}
