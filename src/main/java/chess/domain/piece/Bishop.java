package chess.domain.piece;

import chess.domain.move.BishopMoveStrategy;
import chess.domain.move.MoveStrategy;

public class Bishop extends ValidPiece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new BishopMoveStrategy();
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
