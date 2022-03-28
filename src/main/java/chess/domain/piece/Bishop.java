package chess.domain.piece;

import java.util.List;

public class Bishop extends SlidingPiece {

    private static final String WHITE_SIGNATURE = "b";
    private static final String BLACK_SIGNATURE = "B";
    private static final double SCORE = 3.0;

    private Bishop(Position position, String signature) {
        super(position, signature);
    }

    public static Bishop createWhite(Position position) {
        return new Bishop(position, WHITE_SIGNATURE);
    }

    public static Bishop createBlack(Position position) {
        return new Bishop(position, BLACK_SIGNATURE);
    }

    @Override
    protected List<Direction> findPossibleDirections() {
        return Direction.getDiagonalDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }


}
