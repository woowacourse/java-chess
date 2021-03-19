package chess.domain.piece;

import chess.domain.position.Position;

public class Empty extends Basis {
    public Empty() {
        super(".");
    }

    @Override
    public void move(Position to, Pieces pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kill(Position to, Pieces pieces) {

    }

    @Override
    public boolean hasPosition(Position position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position getPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSameColor(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
