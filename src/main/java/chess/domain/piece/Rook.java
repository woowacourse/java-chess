package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(new RookMoveStrategy(), color, ROOK_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return moveStrategy.isMovable(fromPosition, toPosition);
    }
}
