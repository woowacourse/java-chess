package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public final class Rook extends Strongmen {

    private static final int LEFT_INIT_FILE = 0;
    private static final int RIGHT_INIT_FILE = 7;

    private static final double SCORE = 5;

    private static final String BLACK_DISPLAY = "♖";
    private static final String WHITE_DISPLAY = "♜";

    Rook(Color color, Position position) {
        super(color, position);
    }

    public static Rook ofLeft(Color color) {
        Position position = Position.of(LEFT_INIT_FILE, firstRankOf(color));
        return new Rook(color, position);
    }

    public static Rook ofRight(Color color) {
        Position position = Position.of(RIGHT_INIT_FILE, firstRankOf(color));
        return new Rook(color, position);
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
        return position.isHorizontal(toPosition)
                || position.isVertical(toPosition);
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
        Rook rook = (Rook) o;
        return color == rook.color
                && position == rook.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Rook{color=" + color + ", position=" + position + '}';
    }
}