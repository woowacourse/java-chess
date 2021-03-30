package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.List;

public class Empty extends Basis {
    public Empty() {
        super(".");
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {

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
        return false;
    }

    @Override
    public boolean isSameColor(Piece piece) {
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
    public double score() {
        return 0;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public Column getColumn() {
        throw new UnsupportedOperationException();
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
