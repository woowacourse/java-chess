package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class King implements Piece {
    private static final String ORIGINAL_NAME = "k";
    private static final int DISTANCE = 1;

    private final Team team;

    private King(Team team) {
        this.team = team;
    }

    public static King from(Team team) {
        return new King(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasBlock, boolean hasTarget) {
        if (hasBlock) {
            throw new IllegalArgumentException("킹은 기물을 건너 뛸 수 없습니다.");
        }
        return isKingMove(source, target);
    }

    public boolean isKingMove(Point source, Point target) {
        if (source.isHorizontal(target) && source.isSameFileDistance(target, DISTANCE)) {
            return true;
        }
        if (source.isVertical(target) && source.isSameRankDistance(target, DISTANCE)) {
            return true;
        }
        return source.isDiagonal(target) && source.isSameFileDistance(target, DISTANCE);
    }

    @Override
    public String failMoveMsg() {
        return "킹은 상하좌우, 대각선으로 1칸만 이동 가능합니다.";
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }
}
