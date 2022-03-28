package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(new BishopMoveStrategy(), color, BISHOP_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
