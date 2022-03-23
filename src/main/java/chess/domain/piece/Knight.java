package chess.domain.piece;

import chess.domain.Position;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return false;
    }
}
