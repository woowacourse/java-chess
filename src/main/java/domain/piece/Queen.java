package domain.piece;

import domain.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isDiagonal(target) || source.isStraight(target);
    }
}
