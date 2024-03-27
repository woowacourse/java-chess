package chess.dto;

import chess.view.InputView;

import java.util.Set;

public enum GameCommand {
    START,
    END,
    MOVE,
    STATUS,
    ;

    private static final Set<GameCommand> SINGLE_COMMANDS = Set.of(START, END, STATUS);

    public static GameCommand from(final String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }

    public boolean isSingleCommand() {
        return SINGLE_COMMANDS.contains(this);
    }
}
