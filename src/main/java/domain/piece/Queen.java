package domain.piece;

import domain.position.Position;

public class Queen extends Division{
    public Queen(String color) {
        super(color, "q");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
