package chess.domain.piece;

import chess.domain.position.Position;
import chess.strategy.OccupiedChecker;
import java.util.Objects;

public final class Queen extends Strongmen {

    private static final int INIT_FILE = 3;

    private static final double SCORE = 9;

    private static final String BLACK_DISPLAY = "♕";
    private static final String WHITE_DISPLAY = "♛";

    Queen(Color color, Position position) {
        super(color, position);
    }

    public Queen(Color color) {
        this(color, Position.of(INIT_FILE, firstRankOf(color)));
    }

    @Override
    public void move(Position position, OccupiedChecker isOccupied) {
        validateMovable(position);
        validateClearPathTo(position, isOccupied);
        this.position = position;
    }

    private void validateMovable(Position toPosition) {
        if (!isMovablePosition(toPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    private boolean isMovablePosition(Position toPosition) {
        return position.isHorizontal(toPosition)
                || position.isVertical(toPosition)
                || position.isDiagonal(toPosition);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
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
        Queen queen = (Queen) o;
        return color == queen.color
                && position == queen.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Queen{" + "color=" + color + ", position=" + position + '}';
    }
}

