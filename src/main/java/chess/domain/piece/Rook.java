package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class Rook extends Piece {

    public Rook(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }
}
