package domain.piece;

import domain.position.Position;

public class King extends Division{
    public King(Color color) {
        super(color, "k");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }
}
