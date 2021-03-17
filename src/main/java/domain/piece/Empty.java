package domain.piece;

import domain.position.Position;

import java.util.List;

public class Empty extends Basis{
    public Empty() {
        super(".");
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {

    }

    @Override
    public boolean hasPosition(Position position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position getPosition() {
        throw new UnsupportedOperationException();
    }
}
