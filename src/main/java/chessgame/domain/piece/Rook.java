package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Rook implements Piece {
    private static final String ORIGINAL_NAME = "r";

    private final Team team;

    private Rook(Team team) {
        this.team = team;
    }

    public static Rook from(Team team) {
        return new Rook(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasBlock, boolean hasTarget) {
        if (hasBlock) {
            throw new IllegalArgumentException("룩은 기물을 건너 뛸 수 없습니다.");
        }
        return source.isHorizontal(target) || source.isVertical(target);
    }

    @Override
    public String failMoveMsg() {
        return "룩은 상하좌우로만 이동 가능합니다.";
    }

    @Override
    public Team team() {
        return team;
    }

    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }
}
