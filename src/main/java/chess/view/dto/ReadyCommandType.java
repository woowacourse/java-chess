package chess.view.dto;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;

public enum ReadyCommandType {

    USE, CREATE;

    public static ReadyCommandType from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}
