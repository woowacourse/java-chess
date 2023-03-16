package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class Bishop extends Piece {

    public Bishop(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }
}
