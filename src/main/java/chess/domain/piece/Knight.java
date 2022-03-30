package chess.domain.piece;

import chess.domain.piece.strategy.KnightMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.position.Position;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    private final MoveStrategy moveStrategy;

    public Knight(Color color) {
        super(color, KNIGHT_SCORE);
        this.moveStrategy = new KnightMoveStrategy();
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
