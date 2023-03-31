package chess.view.dto.category;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

public enum CategoryCommandType {

    RECORD, PLAY;

    public static CategoryCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }
}
