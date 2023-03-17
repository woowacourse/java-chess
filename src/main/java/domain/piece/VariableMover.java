package domain.piece;

import domain.coordinate.Position;

import java.util.ArrayList;
import java.util.List;

public interface VariableMover {

    default List<Position> findPositions(final Position source, final Position target, final int moveX, final int moveY) {
        List<Position> positions = new ArrayList<>();
        Position position = source.move(moveX, moveY);

        while (position != target) {
            positions.add(position);
            position = position.move(moveX, moveY);
        }
        return positions;
    }

}
