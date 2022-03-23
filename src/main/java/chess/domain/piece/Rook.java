package chess.domain.piece;

import chess.domain.Position;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return false;
    }
}
