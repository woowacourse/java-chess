package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class Knight extends Piece {

    public Knight(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }
}
