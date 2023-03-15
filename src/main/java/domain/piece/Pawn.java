package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(PieceName.PAWN, color);
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
