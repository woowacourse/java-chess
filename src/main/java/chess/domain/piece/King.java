package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;

public class King extends Piece {

    private static final double KING_SCORE = 0;

    private final MoveStrategy moveStrategy;

    public King(Color color) {
        super(color, KING_SCORE);
        this.moveStrategy = new KingMoveStrategy();
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
