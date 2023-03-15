package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class Pawn extends Piece {

    public Pawn(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }
}
