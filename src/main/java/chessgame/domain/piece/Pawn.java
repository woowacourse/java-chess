package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";
    private final Team team;
    int FIRST_MOVE_DISTANCE = 2;
    int DISTANCE = 1;

    private Pawn(Team team) {
        this.team = team;
    }

    public static Pawn from(Team team) {
        return new Pawn(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasBlock, boolean hasTarget) {
        if (hasTarget) {
            return isAttack(source, target);
        }
        return isPawnMovable(source, target);
    }

    public boolean isPawnMovable(Point source, Point target) {
        if (!source.isVertical(target)) {
            return false;
        }
        if (isPawnStartMove(source, target, team)) {
            return true;
        }
        return source.rankDistance(target) == teamDirection(DISTANCE);
    }

    private boolean isPawnStartMove(Point source, Point target, Team team) {
        if (source.isInitialPoint(team.startRank())
            && source.rankDistance(target) == teamDirection(DISTANCE)) {
            return true;
        }
        return source.isInitialPoint(team.startRank())
            && source.rankDistance(target) == teamDirection(FIRST_MOVE_DISTANCE);
    }

    private int teamDirection(int distance) {
        return distance * team.direction();
    }

    private boolean isAttack(Point source, Point target) {
        if (!source.isDiagonal(target)) {
            return false;
        }
        return source.rankDistance(target) == teamDirection(DISTANCE);
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
        return team.calculate(ORIGINAL_NAME);
    }
}
