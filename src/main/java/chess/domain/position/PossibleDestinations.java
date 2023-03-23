package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleDestinations {
    private static final int BOARD_SIZE = 8;

    private final Set<Position> possibleDestinations;

    private PossibleDestinations(Set<Position> possibleDestinations) {
        this.possibleDestinations = possibleDestinations;
    }

    public static PossibleDestinations of(final Position source, final List<Direction> allDirections, final int moveCount) {
        final Set<Position> possibleDestinations = new HashSet<>();
        for (Direction direction : allDirections) {
            addByEachDirection(moveCount, possibleDestinations, direction, source);
        }
        return new PossibleDestinations(possibleDestinations);
    }

    private static void addByEachDirection(final int moveCount, final Set<Position> positions,
                                           final Direction direction, final Position source) {
        Position possiblePosition = source;
        for (int i = 0; i < moveCount; i++) {
            possiblePosition = move(possiblePosition, direction);
            addIfNotOver(positions, possiblePosition);
        }
    }

    private static void addIfNotOver(final Set<Position> positions, Position possiblePosition) {
        if (!possiblePosition.isOver(BOARD_SIZE)) {
            positions.add(possiblePosition);
        }
    }

    private static Position move(final Position before, final Direction direction) {
        return direction.calculate(before);
    }

    public boolean contains(Position target) {
        return possibleDestinations.contains(target);
    }
}
