package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {

    private static final int SCOPE_DIFFERENCE = 1;
    private static final double KING_SCORE = 0;

    public King(Color color) {
        super(new KingMoveStrategy(), color, KING_SCORE);
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
