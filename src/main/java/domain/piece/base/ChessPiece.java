package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public interface ChessPiece {

    Direction getDirection(Coordinate start, Coordinate destination);

    boolean hasSameColor(Color color);
}
