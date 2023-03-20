package chess.domain.move.piece;

import chess.domain.move.Direction;
import chess.domain.move.Location;
import chess.domain.piece.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Move {
    private static final int BOARD_SIZE = 8;
    static final int KING_MAX_MOVE_COUNT = 1;
    static final int PAWN_MAX_MOVE_COUNT = 2;
    static final int MAX_MOVE_COUNT = 8;

    static Location getLocation(final Position source, final List<Direction> allDirections, final int moveCount) {
        final Location location = new Location();
        for (Direction direction : allDirections) {
            location.add(addByEachDirection(moveCount, direction, source));
        }
        return location;
    }

    private static Set<Position> addByEachDirection(final int moveCount, final Direction direction, final Position source) {
        final Set<Position> positions = new HashSet<>();
        Position possiblePosition = source;
        for (int i = 0; i < moveCount; i++) {
            possiblePosition = move(possiblePosition, direction);
            addIfNotOver(positions, possiblePosition);
        }
        return positions;
    }

    private static void addIfNotOver(final Set<Position> positions, final Position possiblePosition) {
        if (!possiblePosition.isOver(BOARD_SIZE)) {
            positions.add(possiblePosition);
        }
    }

    private static Position move(final Position before, final Direction direction) {
        return direction.calculate(before);
    }
}

