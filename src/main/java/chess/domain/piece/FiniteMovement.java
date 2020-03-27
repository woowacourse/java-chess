package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public abstract class FiniteMovement extends OrdinaryMovement {

    @Override
    public List<Position> findMovePath(Position source, Position target) {
        for (Direction direction : getDirections()) {
            Position position = source.destinationOf(direction).orElse(null);
            if (target.equals(position)) {
                return Collections.emptyList();
            }
        }

        throw new InvalidMovementException();
    }

    public abstract List<Direction> getDirections();
}
