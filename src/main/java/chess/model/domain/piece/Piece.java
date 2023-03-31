package chess.model.domain.piece;

import chess.model.domain.position.Movement;
import chess.model.domain.position.Path;
import chess.model.domain.position.Position;
import chess.model.exception.CantMoveFromToException;
import chess.model.exception.CantMoveToSameColor;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    private final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    protected final void validateMovable(final Movement movement, final List<Movement> canMovements) {
        if (!canMovements.contains(movement)) {
            throw new CantMoveFromToException();
        }
    }

    public final boolean isBlack() {
        return color == Color.BLACK;
    }

    public final boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public final Path searchPathTo(final Position from, final Position to, final Piece destination) {
        if (!destination.isEmpty()) {
            validateSameColor(destination);
        }
        final Movement movement = searchMovement(from, to, destination);
        return generatePathFromTo(from, to, movement);
    }

    private void validateSameColor(final Piece other) {
        if (color.isSameColor(other.color)) {
            throw new CantMoveToSameColor();
        }
    }

    private Path generatePathFromTo(final Position from, final Position to, final Movement movement) {
        Position next = from;
        final Deque<Position> positions = new LinkedList<>();
        while (!next.equals(to)) {
            next = movement.move(next);
            positions.add(next);
        }
        positions.removeLast();
        return new Path(positions);
    }

    public final PieceType getPieceType() {
        return pieceType;
    }

    public final Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }

    public abstract boolean isEmpty();

    public abstract Movement searchMovement(final Position from, final Position to, final Piece destination);
}
