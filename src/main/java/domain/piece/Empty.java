package domain.piece;

import domain.position.Position;

public class Empty extends Basis{
    public Empty() {
        super(".");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        throw new UnsupportedOperationException();
    }
}
