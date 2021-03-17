package domain.piece;

import domain.position.Position;

public class Knight extends Division{
    public Knight(Color color) {
        super(color, "n");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
