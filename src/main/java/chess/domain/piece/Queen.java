package chess.domain.piece;

import chess.domain.position.Direction;

public class Queen extends Piece {
    private static final char NAME_WHEN_BLACK = 'Q';
    private static final char NAME_WHEN_WHITE = 'q';
    private static final int LINE_COUNT = 8;

    public Queen(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y, Direction.everyDirection(), LINE_COUNT);
    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
