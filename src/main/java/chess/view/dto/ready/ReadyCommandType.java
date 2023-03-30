package chess.view.dto.ready;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

public enum ReadyCommandType {

    USE, CREATE;

    public static ReadyCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }
}
