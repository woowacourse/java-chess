package chess.domain.piece;

import chess.domain.Team;
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

    public static Bishop create(Team team, Position position) {
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
        return Direction.getDiagonalDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
