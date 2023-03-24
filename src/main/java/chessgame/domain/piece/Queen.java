package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public class Queen implements Piece {
    private static final String ORIGINAL_NAME = "q";

    private final Team team;
    private final double score = 9;

    private Queen(Team team) {
        this.team = team;
    }

    public static Queen from(Team team) {
        return new Queen(team);
    }

    @Override
    public boolean isMovable(Points points, boolean isBlocked, boolean hasTarget) {
        if (isBlocked) {
            throw new IllegalArgumentException("퀸은 기물을 건너 뛸 수 없습니다.");
        }
        return points.isHorizontal() || points.isVertical() || points.isDiagonal();
    }

    @Override
    public Team team() {
        return team;
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
    public String failMoveMsg() {
        return "퀸은 상하좌우, 대각선으로만 이동 가능합니다.";
    }

    @Override
    public String toString() {
        return team.convertName(ORIGINAL_NAME);
    }
}
