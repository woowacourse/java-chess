package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";
    private static final int BLACK_DISTANCE = 1;
    private static final Rank BLACK_INITIAL_RANK = Rank.SEVEN;
    private static final int WHITE_DISTANCE = -1;
    private static final Rank WHITE_INITIAL_RANK = Rank.TWO;

    private final Team team;

    private Pawn(Team team) {
        this.team = team;
    }

    public static Pawn from(Team team) {
        return new Pawn(team);
    }

    public boolean isAttack(Point source, Point target) {
        return isPawnAttack(source, target, team);
    }

    public boolean isPawnAttack(Point source, Point target, Team team) {
        if (team == Team.BLACK) {
            return canPawnAttack(source, target, BLACK_DISTANCE);
        }
        if (team == Team.WHITE) {
            return canPawnAttack(source, target, WHITE_DISTANCE);
        }
        return false;
    }

    public boolean isPawnMove(Point source, Point target, Team team) {
        if (team == Team.BLACK && source.fileDistance(target) == 0) {
            return canPawnMove(source, target, BLACK_DISTANCE, BLACK_INITIAL_RANK);
        }
        if (team == Team.WHITE && source.fileDistance(target) == 0) {
            return canPawnMove(source, target, WHITE_DISTANCE, WHITE_INITIAL_RANK);
        }
        return false;
    }

    private boolean canPawnMove(Point source, Point target, int distance, Rank rank) {
        if (source.isInitialPoint(rank) && (source.rankDistance(target) == distance
                || source.rankDistance(target) == distance * 2)) {
            return true;
        }
        return source.rankDistance(target) == distance;
    }

    private boolean canPawnAttack(Point source, Point target, int distance) {
        if (source.rankDistance(target) == distance && source.fileDistance(target) == distance) {
            return true;
        }
        return source.rankDistance(target) == distance && source.fileDistance(target) == -distance;
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return isPawnMove(source, target, team);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public String toString() {
        return team.convertTeamName(ORIGINAL_NAME);
    }
}
