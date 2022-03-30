package chess.domain.piece;

import chess.domain.piece.strategy.BishopMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    private final MoveStrategy moveStrategy;

    public Bishop(Color color) {
        super(color, BISHOP_SCORE);
        this.moveStrategy = new BishopMoveStrategy();
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
