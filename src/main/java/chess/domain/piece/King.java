package chess.domain.piece;

import chess.domain.Position;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return false;
    }
}
