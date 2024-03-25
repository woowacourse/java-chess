package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public interface ChessPiece {

    boolean isNotSameColor(Color color);

    boolean isBlack();

    Direction getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);
}
