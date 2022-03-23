package chess.domain.piece;

import chess.domain.piece.position.Position;
import java.util.Objects;

public class Knight extends Piece {

    private static final int MAIN_DIRECTION_MOVE_COUNT = 2;
    private static final int SUB_DIRECTION_MOVE_COUNT = 1;

    private static final String BLACK_DISPLAY = "♘";
    private static final String WHITE_DISPLAY = "♞";

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position position) {
        validateMovable(position);
        this.position = position;
    }

    private void validateMovable(Position toPosition) {
        if (!isMovablePosition(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean isMovablePosition(Position toPosition) {
        int fileDifference = position.fileDifference(toPosition);
        int rankDifference = position.rankDifference(toPosition);

        return (fileDifference == SUB_DIRECTION_MOVE_COUNT && rankDifference == MAIN_DIRECTION_MOVE_COUNT)
                || (fileDifference == MAIN_DIRECTION_MOVE_COUNT && rankDifference == SUB_DIRECTION_MOVE_COUNT);
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
        Knight knight = (Knight) o;
        return color == knight.color
                && position == knight.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Knight{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}
