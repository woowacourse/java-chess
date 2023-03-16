package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }

    @Override
    public boolean canMove(Square current, Square destination) {

        return false;
    }
}
