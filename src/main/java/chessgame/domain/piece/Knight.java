package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public class Knight implements Piece {
    private static final String ORIGINAL_NAME = "n";
    private static final int STRAIGHT_DISTANCE = 2;
    private static final int DIAGONAL_DISTANCE = 1;

    private final Team team;

    private Knight(Team team) {
        this.team = team;
    }

    public static Knight from(Team team) {
        return new Knight(team);
    }

    @Override
    public boolean isMovable(Points points, boolean hasBlock, boolean hasTarget) {
        int fileDistance = Math.abs(points.fileDistance());
        int rankDistance = Math.abs(points.rankDistance());

        if (fileDistance == STRAIGHT_DISTANCE && rankDistance == DIAGONAL_DISTANCE) {
            return true;
        }
        return fileDistance == DIAGONAL_DISTANCE && rankDistance == STRAIGHT_DISTANCE;
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public String failMoveMsg() {
        return "나이트는 상하좌우 1칸 이동 후 진행방향으로 대각선 1칸이동만 가능합니다.";
    }

    @Override
    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }
}
