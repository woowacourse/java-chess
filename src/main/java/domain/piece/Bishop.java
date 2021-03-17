package domain.piece;

import domain.position.Position;

public class Bishop extends Division{
    public Bishop(String color) {
        super(color, "b");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
