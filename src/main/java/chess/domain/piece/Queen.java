package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.QueenMoveStrategy;

public final class Queen extends ValidPiece {

    private static final double POINT = 9;

    public Queen(final Color color) {
        super(color, POINT);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new QueenMoveStrategy();
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
