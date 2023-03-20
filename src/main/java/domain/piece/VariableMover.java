package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;

import java.util.ArrayList;
import java.util.List;

public interface VariableMover {

    default List<Position> findPositions(MovePosition movePosition, final int moveX, final int moveY) {
        List<Position> positions = new ArrayList<>();
        Position position = movePosition.getSource().move(moveX, moveY);

        while (position != movePosition.getTarget()) {
            positions.add(position);
            position = position.move(moveX, moveY);
        }

        return positions;
    }

}
