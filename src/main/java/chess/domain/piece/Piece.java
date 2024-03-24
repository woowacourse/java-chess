package chess.domain.piece;

import chess.domain.Movement;
import java.util.Objects;

public abstract class Piece {
    private static final String NOT_ALLOW_MOVE_SAME_COLOR = "같은 색상의 기물의 위치로 이동할 수 없습니다.";
    private static final String INVALID_MOVE = "해당 위치로 이동할 수 없습니다.";

    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(final Movement movement, final Piece destinationPiece);

    public void validate(final Movement movement, final Piece destinationPiece) {
        validateSameColor(destinationPiece);
        validateDestinationMove(movement, destinationPiece);
    }

    private void validateSameColor(final Piece destinationPiece) {
        if (this.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException(NOT_ALLOW_MOVE_SAME_COLOR);
        }
    }

    private void validateDestinationMove(final Movement movement, final Piece destinationPiece) {
        if (!this.canMove(movement, destinationPiece)) {
            throw new IllegalArgumentException(INVALID_MOVE);
        }
    }

    public boolean isSameColor(final Piece piece) {
        if (piece == null) {
            return false;
        }
        return color == piece.color;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public Color color() {
        return color;
    }

    public Type type() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
