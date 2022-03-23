package chess.domain.piece;

import chess.domain.Position;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return false;
    }
}
