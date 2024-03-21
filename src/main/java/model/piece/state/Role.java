package model.piece.state;

import java.util.Set;
import model.Position;

public interface Role {
    Set<Position> possiblePositions(Position position);
}
