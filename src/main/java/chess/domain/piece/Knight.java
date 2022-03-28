package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.KnightMoveStrategy;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(new KnightMoveStrategy(), color, KNIGHT_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
