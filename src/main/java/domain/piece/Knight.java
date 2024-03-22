package domain.piece;

import domain.position.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isStraightDiagonal(target);
    }

    @Override
    public String display() {
        return "N";
    }
}
