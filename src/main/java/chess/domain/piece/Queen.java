package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Queen extends SlidingPiece {

    private static final String WHITE_SIGNATURE = "q";
    private static final String BLACK_SIGNATURE = "Q";
    private static final double SCORE = 9.0;

    private Queen(Position position, String signature) {
        super(position, signature);
    }

    public static Queen createWhite(Position position) {
        return new Queen(position, WHITE_SIGNATURE);
    }

    public static Queen createBlack(Position position) {
        return new Queen(position, BLACK_SIGNATURE);
    }

    @Override
    protected List<Direction> findPossibleDirections() {
        return Direction.getEightStraightDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
