package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.RookMoveStrategy;

public class Rook extends ValidPiece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new RookMoveStrategy();
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
