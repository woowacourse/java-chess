package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Knight extends Piece {

    public Knight(Color color) {
        super(PieceName.KNIGHT, color);
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
