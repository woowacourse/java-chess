package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class King extends Piece {

    public King(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }
}
