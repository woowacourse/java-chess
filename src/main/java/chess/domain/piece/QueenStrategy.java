package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public class QueenStrategy implements MoveStrategy {

    private final List<Direction> directions = Arrays.asList(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST);

    @Override
    public List<Position> findMovePath(final Position source, final Position target, final boolean isKill) {
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