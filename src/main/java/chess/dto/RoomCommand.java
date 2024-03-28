package chess.dto;

import chess.view.InputView;

public enum RoomCommand {

    ENTER, CREATE;

    public static RoomCommand from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }
}
