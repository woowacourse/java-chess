package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.QueenMoveStrategy;

public class Queen extends ValidPiece {

    public Queen(final Color color) {
        super(color);
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
