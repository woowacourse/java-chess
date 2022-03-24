package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;

import chess.domain.position.Position;

public abstract class Strongmen extends Chessmen {

    private static final int BLACK_FIRST_RANK = 7;
    private static final int WHITE_FIRST_RANK = 0;

    protected Strongmen(Color color, Position position) {
        super(color, position);
    }

    protected static int firstRankOf(Color color) {
        if (color == BLACK) {
            return BLACK_FIRST_RANK;
        }
        return WHITE_FIRST_RANK;
    }

    @Override
    protected final void attack(Position enemyPosition) {
        move(enemyPosition);
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
