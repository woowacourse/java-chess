package chess.domain.piece;

import chess.domain.piece.position.Position;
import java.util.Objects;

public class King extends Piece {

    private static final String BLACK_DISPLAY = "♔";
    private static final String WHITE_DISPLAY = "♚";
    private static final int MAX_MOVE_DIFFERENCE = 1;

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position position) {
        validateMovable(position);
        this.position = position;
    }

    private void validateMovable(Position toPosition) {
        if (!canMoveOneStep(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean canMoveOneStep(Position toPosition) {
        int fileDifference = position.fileDifference(toPosition);
        int rankDifference = position.rankDifference(toPosition);

        return fileDifference <= MAX_MOVE_DIFFERENCE
                && rankDifference <= MAX_MOVE_DIFFERENCE;
    }

    @Override
    public String display() {
        if (color == Color.BLACK) {
            return BLACK_DISPLAY;
        }
        return WHITE_DISPLAY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return color == king.color
                && position == king.position;

    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "King{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}

