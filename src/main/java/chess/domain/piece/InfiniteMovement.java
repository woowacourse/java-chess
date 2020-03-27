package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public abstract class InfiniteMovement extends OrdinaryMovement {

    @Override
    public List<Position> findMovePath(Position source, Position target) {
        for (Direction direction : getDirection()) {
            List<Position> path = new ArrayList<>();
            Position position = source;

            do {
                position = position.destinationOf(direction).orElse(null);
                if (target.equals(position)) {
                    return path;
                }
                path.add(position);
            } while (position != null);
        }

        throw new InvalidMovementException();
    }

    public abstract List<Direction> getDirection();
}
