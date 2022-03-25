package chess.domain.piece;

import chess.domain.position.Position;
import chess.strategy.OccupiedChecker;
import java.util.Objects;

public final class King extends Strongmen {

    private static final int INIT_FILE = 4;

    private static final int MAX_MOVE_DIFFERENCE = 1;

    private static final String BLACK_DISPLAY = "♔";
    private static final String WHITE_DISPLAY = "♚";

    private static final double SCORE = 0;

    King(Color color, Position position) {
        super(color, position);
    }

    public King(Color color) {
        this(color, Position.of(INIT_FILE, firstRankOf(color)));
    }

    @Override
    public void move(Position position, OccupiedChecker isOccupied) {
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
    public boolean isKing() {
        return true;
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
        return "King{" + "color=" + color + ", position=" + position + '}';
    }
}
