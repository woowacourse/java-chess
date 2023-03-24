package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";
    private static final int FIRST_MOVE_DISTANCE = 2;
    private static final int DISTANCE = 1;

    private final Team team;
    private final double score = 1;

    private Pawn(Team team) {
        this.team = team;
    }

    public static Pawn from(Team team) {
        return new Pawn(team);
    }

    @Override
    public boolean isMovable(Points points, boolean isBlocked, boolean hasTarget) {
        if (hasTarget) {
            return isAttack(points);
        }
        return isPawnMovable(points);
    }

    public boolean isPawnMovable(Points points) {
        if (!points.isVertical()) {
            return false;
        }
        if (isPawnStartMove(points, team)) {
            return true;
        }
        return points.rankDistance() == teamDirection(DISTANCE);
    }

    private boolean isPawnStartMove(Points points, Team team) {
        return points.isInitialPoint(team.startRank())
            && points.rankDistance() == teamDirection(FIRST_MOVE_DISTANCE);
    }

    private int teamDirection(int distance) {
        return distance * team.direction();
    }

    private boolean isAttack(Points points) {
        if (!points.isDiagonal()) {
            return false;
        }
        return points.rankDistance() == teamDirection(DISTANCE);
    }

    @Override
    public double score(Team team) {
        if (this.team == team) {
            return score;
        }
        return 0;
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public String failMoveMsg() {
        return "폰은 이동시 앞으로만 한칸 이동 가능하나, 처음은 두칸도 가능합니다. 공격시엔 앞방향 대각선으로 한칸 이동가능 합니다.";
    }

    @Override
    public String toString() {
        return team.convertName(ORIGINAL_NAME);
    }
}
