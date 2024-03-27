package chess.dto;

import chess.view.InputView;

public enum UserCommand {

    LOGIN, SIGNUP;

    public static UserCommand from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }
}
