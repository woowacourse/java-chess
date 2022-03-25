package chess.domain.piece;

import java.util.List;

public class Rook extends SlidingPiece {

    private static final String WHITE_SIGNATURE = "r";
    private static final String BLACK_SIGNATURE = "R";
    private static final double SCORE = 5.0;

    private Rook(Position position, String signature) {
        super(position, signature);
    }

    public static Rook createWhite(Position position) {
        return new Rook(position, WHITE_SIGNATURE);
    }

    public static Rook createBlack(Position position) {
        return new Rook(position, BLACK_SIGNATURE);
    }

    @Override
    protected List<Direction> findPossibleDirections() {
        return Direction.getPerpendicularDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
