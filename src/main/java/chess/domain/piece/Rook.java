package chess.domain.piece;

import chess.domain.Team;
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

    public static Rook create(Team team, Position position) {
        if (team == Team.BLANK || team == null) {
            throw new IllegalArgumentException("기물은 팀이 있어야 합니다.");
        }
        if (team == Team.BLACK) {
            return createBlack(position);
        }
        return createWhite(position);
    }

    @Override
    protected List<Direction> findPossibleDirections() {
        return Direction.getPerpendicularDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
