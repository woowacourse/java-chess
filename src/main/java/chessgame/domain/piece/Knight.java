package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Knight implements Piece {
    private static final String ORIGINAL_NAME = "n";
    private static final int STRAIGHT = 2;
    private static final int DIAGONAL = 1;

    private final Team team;

    private Knight(Team team) {
        this.team = team;
    }

    public static Knight from(Team team) {
        return new Knight(team);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return isKnightMove(source, target);
    }

    @Override
    public Team team() {
        return team;
    }

    private boolean isKnightMove(Point source, Point target) {
        int fileDistance = Math.abs(source.fileDistance(target));
        int rankDistance = Math.abs(source.rankDistance(target));

        if (fileDistance == STRAIGHT && rankDistance == DIAGONAL) {
            return true;
        }
        return fileDistance == DIAGONAL && rankDistance == STRAIGHT;
    }

    @Override
    public String toString() {
        return team.convertTeamName(ORIGINAL_NAME);
    }
}
