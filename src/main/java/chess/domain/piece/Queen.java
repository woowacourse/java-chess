package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(new QueenMoveStrategy(), color, QUEEN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
