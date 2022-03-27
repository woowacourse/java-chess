package chess.domain.piece.strongmen;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import java.util.Objects;

public final class Knight extends Strongmen {

    private static final int LEFT_INIT_FILE = 1;
    private static final int RIGHT_INIT_FILE = 6;

    private static final int MAIN_DIRECTION_MOVE_COUNT = 2;
    private static final int SUB_DIRECTION_MOVE_COUNT = 1;

    private static final double SCORE = 2.5;

    private static final String BLACK_DISPLAY = "♘";
    private static final String WHITE_DISPLAY = "♞";

    Knight(Color color, Position position) {
        super(color, position);
    }

    public static Knight ofLeft(Color color) {
        Position position = Position.of(LEFT_INIT_FILE, initRankOf(color));
        return new Knight(color, position);
    }

    public static Knight ofRight(Color color) {
        Position position = Position.of(RIGHT_INIT_FILE, initRankOf(color));
        return new Knight(color, position);
    }

    @Override
    protected boolean canMoveTo(Position targetPosition) {
        int fileDifference = position.fileDifference(targetPosition);
        int rankDifference = position.rankDifference(targetPosition);

        return (fileDifference == SUB_DIRECTION_MOVE_COUNT && rankDifference == MAIN_DIRECTION_MOVE_COUNT)
                || (fileDifference == MAIN_DIRECTION_MOVE_COUNT && rankDifference == SUB_DIRECTION_MOVE_COUNT);
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
        return "Knight{color=" + color + ", position=" + position + '}';
    }
}
