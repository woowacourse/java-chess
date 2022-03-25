package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public final class Bishop extends Strongmen {

    private static final int LEFT_INIT_FILE = 2;
    private static final int RIGHT_INIT_FILE = 5;

    private static final double SCORE = 3;

    private static final String BLACK_DISPLAY = "♙";
    private static final String WHITE_DISPLAY = "♟";

    Bishop(Color color, Position position) {
        super(color, position);
    }

    public static Bishop ofLeft(Color color) {
        Position position = Position.of(LEFT_INIT_FILE, firstRankOf(color));
        return new Bishop(color, position);
    }

    public static Bishop ofRight(Color color) {
        Position position = Position.of(RIGHT_INIT_FILE, firstRankOf(color));
        return new Bishop(color, position);
    }

    @Override
    protected boolean canMoveTo(Position targetPosition) {
        return position.isDiagonal(targetPosition);
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
        Bishop bishop = (Bishop) o;
        return color == bishop.color
                && position == bishop.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Bishop{color=" + color + ", position=" + position + '}';
    }
}
