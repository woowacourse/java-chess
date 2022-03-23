package chess.domain.piece;

import chess.domain.Position;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return false;
    }
}
