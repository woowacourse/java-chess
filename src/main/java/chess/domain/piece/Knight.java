package chess.domain.piece;

import chess.domain.position.Direction;

public class Knight extends Piece {
    private static final char NAME_WHEN_BLACK = 'N';
    private static final char NAME_WHEN_WHITE = 'n';

    public Knight(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y, Direction.knightDirection(), 1);
    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}

