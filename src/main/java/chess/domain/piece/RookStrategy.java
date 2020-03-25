package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

import static chess.domain.piece.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RookStrategy implements MoveStrategy {

    private final List<Direction> directions = Arrays.asList(NORTH, EAST, WEST, SOUTH);

    @Override
    public List<Position> findMovePath(final Position source, final Position target) {
        for (Direction direction : directions) {
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
}