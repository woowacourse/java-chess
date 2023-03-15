package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Bishop extends Piece {

    public Bishop(Color color) {
        super(PieceName.BISHOP, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        return false;
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        return false;
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return false;
    }
}
