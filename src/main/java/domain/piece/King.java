package domain.piece;

import domain.position.Position;

import java.util.List;

public final class King extends Piece {

    public King(Color color) {
        super(PieceName.KING, color);
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
