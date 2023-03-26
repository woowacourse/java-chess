package chess.view.dto.category;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;

public enum CategoryCommandType {

    RECORD, PLAY;

    public static CategoryCommandType from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}
