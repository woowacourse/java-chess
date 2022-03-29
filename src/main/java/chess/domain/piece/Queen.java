package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    private final MoveStrategy moveStrategy;

    public Queen(Color color) {
        super(color, QUEEN_SCORE);
        this.moveStrategy = new QueenMoveStrategy();
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
