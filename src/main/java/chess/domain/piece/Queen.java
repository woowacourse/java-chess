package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public class Queen extends Piece {

    private final MoveStrategy subMoveStrategy;

    public Queen(final Color color, final MoveStrategy moveStrategy, final MoveStrategy subMoveStrategy) {
        super(color, moveStrategy);
        this.subMoveStrategy = subMoveStrategy;
    }
}
