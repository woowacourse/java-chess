package chess.domain.piece;

import chess.domain.Team;
import java.util.List;

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

    public static Queen create(Team team, Position position) {
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
        return Direction.getEightStraightDirections();
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
