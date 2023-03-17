package chess.view.dto;

import static chess.view.InputView.INVALID_INPUT_MESSAGE;

import java.util.Arrays;

public enum CommandType {

    START, END, MOVE;

    public static CommandType from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}
