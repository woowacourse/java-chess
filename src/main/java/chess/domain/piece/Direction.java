package chess.domain.piece;

import java.util.List;

public enum Direction {

    UPPER_LEFT(-1, 1),
    UPPER_RIGHT(1, 1),
    LOWER_LEFT(-1, -1),
    LOWER_RIGHT(1, -1),

    KNIGHT_UPPER_LEFT(-1, 2),
    KNIGHT_UPPER_RIGHT(1, 2),
    KNIGHT_LOWER_LEFT(-1, -2),
    KNIGHT_LOWER_RIGHT(1, -2),
    KNIGHT_LEFT_UPPER(-2, 1),
    KNIGHT_LEFT_LOWER(-2, -1),
    KNIGHT_RIGHT_UPPER(2, 1),
    KNIGHT_RIGHT_LOWER(2, -1);

    private final int directionOfRank;
    private final int directionOfFile;

    Direction(int directionOfRank, int directionOfFile) {
        this.directionOfRank = directionOfRank;
        this.directionOfFile = directionOfFile;
    }

    public static List<Direction> findKnightDirections() {
        return List.of(
                KNIGHT_UPPER_LEFT,
                KNIGHT_UPPER_RIGHT,
                KNIGHT_LOWER_LEFT,
                KNIGHT_LOWER_RIGHT,
                KNIGHT_LEFT_UPPER,
                KNIGHT_LEFT_LOWER,
                KNIGHT_RIGHT_UPPER,
                KNIGHT_RIGHT_LOWER
        );
    }

    public int getDirectionOfRank() {
        return directionOfRank;
    }

    public int getDirectionOfFile() {
        return directionOfFile;
    }
}
