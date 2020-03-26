package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public class KnightStrategy implements MoveStrategy {

    private final List<Direction> directions = Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);

    @Override
    public List<Position> findMovePath(final Position source, final Position target, final boolean isKill) {
        for (Direction direction : directions) {
            Position position = source.destinationOf(direction).orElse(null);
            if (target.equals(position)) {
                return Collections.emptyList();
            }
        }

        throw new InvalidMovementException();
    }
}