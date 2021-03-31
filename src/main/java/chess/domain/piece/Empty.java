package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;

public class Empty extends Basis {
    public Empty() {
        super(".");
    }

    @Override
    public boolean isSameColor(Color color) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public List<List<Position>> movablePositions(Position position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        throw new UnsupportedOperationException();
    }
}
