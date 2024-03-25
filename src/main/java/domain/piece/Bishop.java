package domain.piece;

import domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isDiagonal(target);
    }
}
