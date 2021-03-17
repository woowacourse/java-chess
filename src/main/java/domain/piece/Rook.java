package domain.piece;

import domain.position.Position;

public class Rook extends Division{
    public Rook(Color color) {
        super(color, "r");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
