package domain.piece;

import domain.position.Position;

public class Knight extends Division{
    public Knight(String color) {
        super(color, "n");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
