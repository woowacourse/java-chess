package chess.domain.move;

import chess.domain.piece.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Move {
    private static final int BOARD_SIZE = 8;

    Set<Position> getAllPositions(final Position source, final List<Direction> allDirections, final int moveCount) {
        final Set<Position> positions = new HashSet<>();
        for (Direction direction : allDirections) {
            Position possiblePosition = source;
            for (int i = 0; i < moveCount; i++) {
                possiblePosition = move(possiblePosition, direction);
                if (!possiblePosition.isOverBoard(BOARD_SIZE)) {
                    positions.add(possiblePosition);
                }
            }
        }
        return positions;
    }

    private Position move(final Position before, final Direction direction) {
        return direction.calculate(before);
    }
}

