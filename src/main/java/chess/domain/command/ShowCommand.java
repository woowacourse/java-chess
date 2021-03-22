package chess.domain.command;

import chess.domain.board.position.Position;

public class ShowCommand {
    private static final String SEPARATOR_OF_COMMAND = " ";
    private static final int INDEX_OF_TARGET_POSITION = 1;

    private final Position source;

    private ShowCommand(final Position source) {
        this.source = source;
    }

    public static ShowCommand of(final String[] input) {
        return new ShowCommand(new Position(input[INDEX_OF_TARGET_POSITION]));
    }

    public static ShowCommand of(final String input) {
        return of(input.split(SEPARATOR_OF_COMMAND));
    }

    public Position source() {
        return source;
    }
}
