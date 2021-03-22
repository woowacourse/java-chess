package chess.domain.command;

import chess.domain.board.position.Position;

public class MoveCommand {
    private static final String SEPARATOR_OF_POSITIONS = " ";
    private static final int INDEX_OF_SOURCE_POSITION = 1;
    private static final int INDEX_OF_TARGET_POSITION = 2;

    private final Position source;
    private final Position target;

    private MoveCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveCommand of(final String[] input) {
        return new MoveCommand(
                new Position(input[INDEX_OF_SOURCE_POSITION]),
                new Position(input[INDEX_OF_TARGET_POSITION])
        );
    }

    public static MoveCommand of(final String input) {
        return of(input.split(SEPARATOR_OF_POSITIONS));
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
