package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public abstract class ChessPieceBase implements ChessPiece {

    private final Color color;

    public ChessPieceBase(Color color) {
        this.color = color;
    }

    @Override
    public abstract Direction getDirection(Coordinate start, Coordinate destination);

    @Override
    public boolean hasSameColor(Color color) {
        return this.color == color;
    }
}
