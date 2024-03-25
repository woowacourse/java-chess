package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public interface ChessPiece {

    boolean isOpponentColor(Color color);

    boolean isBlack();

    Direction getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);
}
