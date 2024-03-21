package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import java.util.List;

public interface ChessPiece {

    boolean isSameColor(Color color);

    boolean isBlack();

    List<Integer> getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);
}
