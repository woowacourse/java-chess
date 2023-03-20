package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class Piece {

    private static final String CANT_MOVE_FROM_TO_MESSAGE = "해당 위치로 이동할 수 없습니다.";
    private static final String CANT_MOVE_TO_IS_SAME_TEAM_MESSAGE = "같은 색 말의 위치로 이동할 수 없습니다.";

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    protected final void validateMovable(final Movement movement, final List<Movement> canMovements) {
        if (!canMovements.contains(movement)) {
            throw new IllegalArgumentException(CANT_MOVE_FROM_TO_MESSAGE);
        }
    }

    public final boolean isBlack() {
        return color == Color.BLACK;
    }

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        if (!destination.isEmpty()) {
            validateSameColor(destination);
        }
        final Movement movement = searchMovement(from, to, destination);
        return generatePathFromTo(from, to, movement);
    }

    private void validateSameColor(final Piece other) {
        if (color.isSameColor(other.color)) {
            throw new IllegalArgumentException(CANT_MOVE_TO_IS_SAME_TEAM_MESSAGE);
        }
    }

    private Path generatePathFromTo(final Position from, final Position to, final Movement movement) {
        Position next = from;
        final Deque<Position> positions = new LinkedList<>();
        while (!next.equals(to)) {
            next = next.moveBy(movement);
            positions.add(next);
        }
        positions.removeLast();
        return new Path(positions);
    }

    public abstract boolean isEmpty();

    public abstract Movement searchMovement(final Position from, final Position to, final Piece destination);
}
