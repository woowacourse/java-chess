package chess.view.dto.user;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;

public enum UserCommandType {

    USE, CREATE;

    public static UserCommandType from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}
