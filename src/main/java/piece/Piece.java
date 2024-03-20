package piece;

import coordinate.Coordinate;
import java.util.List;

public interface Piece {

    boolean isSameColor(boolean color);

    List<Integer> getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);
}
