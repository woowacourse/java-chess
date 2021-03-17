package domain.piece;

import domain.position.Position;

import java.util.List;

public class Empty extends Basis{
    public Empty() {
        super(".");
    }

    @Override
    public void move(Position to, List<Position> pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPosition(Position position) {
        throw new UnsupportedOperationException();
    }
}
