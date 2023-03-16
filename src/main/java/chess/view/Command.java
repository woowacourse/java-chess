package chess.view;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;

public enum Command {

    START, END, MOVE;

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}
