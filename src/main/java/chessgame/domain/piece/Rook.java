package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public class Rook implements Piece {
    private static final String ORIGINAL_NAME = "r";

    private final Team team;
    private final double score = 5;

    private Rook(Team team) {
        this.team = team;
    }

    public static Rook from(Team team) {
        return new Rook(team);
    }

    @Override
    public boolean isMovable(Points points, boolean isBlocked, boolean hasTarget) {
        if (isBlocked) {
            throw new IllegalArgumentException("룩은 기물을 건너 뛸 수 없습니다.");
        }
        return points.isHorizontal() || points.isVertical();
    }

    @Override
    public String failMoveMsg() {
        return "룩은 상하좌우로만 이동 가능합니다.";
    }

    @Override
    public double score(Team team, boolean hasPawn) {
        if (this.team == team) {
            return score;
        }
        return 0;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public Team team() {
        return team;
    }

    public String toString() {
        return team.convertName(ORIGINAL_NAME);
    }
}
