package domain.piece;

import domain.position.Path;
import domain.position.Position;

public abstract class SlidingPiece extends Piece{
    public SlidingPiece(PieceName name, Color color) {
        super(name, color);
    }

    @Override
    public boolean isMovablePath(Position start, Path path) {
        return isMovableDirection(start, path.getFirstPosition());
    }
}
