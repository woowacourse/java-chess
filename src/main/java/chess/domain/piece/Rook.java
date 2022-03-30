package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.RookMoveStrategy;
import chess.domain.position.Position;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    private final MoveStrategy moveStrategy;

    public Rook(Color color) {
        super(color, ROOK_SCORE);
        this.moveStrategy = new RookMoveStrategy();
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
