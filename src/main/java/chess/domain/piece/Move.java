package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Move {
    private static final int BOARD_SIZE = 8;

    Set<Position> getAllPositions(final Position source, final List<Direction> allDirections, final int moveCount) {
        final Set<Position> positions = new HashSet<>();
        for (Direction direction : allDirections) {
            addByEachDirection(moveCount, positions, direction, source);
        }
        return positions;
    }

    private void addByEachDirection(final int moveCount, final Set<Position> positions,
                                    final Direction direction, final Position source) {
        Position possiblePosition = source;
        for (int i = 0; i < moveCount; i++) {
            possiblePosition = move(possiblePosition, direction);
            addIfNotOver(positions, possiblePosition);
        }
    }

    private void addIfNotOver(final Set<Position> positions, Position possiblePosition) {
        if (!possiblePosition.isOver(BOARD_SIZE)) {
            positions.add(possiblePosition);
        }
    }

    private Position move(final Position before, final Direction direction) {
        return direction.calculate(before);
    }
}
