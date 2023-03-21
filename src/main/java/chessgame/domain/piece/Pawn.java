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

    private boolean isPawnAttack(Point source, Point target, Team team) {
        if (team == Team.BLACK) {
            return canPawnAttack(source, target, BLACK_DISTANCE);
        }
        if (team == Team.WHITE) {
            return canPawnAttack(source, target, WHITE_DISTANCE);
        }
        return false;
    }

    private boolean isPawnMove(Point source, Point target, Team team) {
        if (team == Team.BLACK && source.fileDistance(target) == 0) {
            return canPawnBlackMove(source, target);
        }
        if (team == Team.WHITE && source.fileDistance(target) == 0) {
            return canPawnWhiteMove(source, target);
        }
        return false;
    }

    private boolean canPawnBlackMove(Point source, Point target) {
        if (source.isInitialPoint(BLACK_INITIAL_RANK) && (source.rankDistance(target) == BLACK_DISTANCE
                || source.rankDistance(target) == BLACK_DISTANCE * 2)) {
            return true;
        }
        return source.rankDistance(target) == BLACK_DISTANCE;
    }

    private boolean canPawnWhiteMove(Point source, Point target) {
        if (source.isInitialPoint(WHITE_INITIAL_RANK) && (source.rankDistance(target) == WHITE_DISTANCE
                || source.rankDistance(target) == WHITE_DISTANCE * 2)) {
            return true;
        }
        return source.rankDistance(target) == WHITE_DISTANCE;
    }

    private boolean canPawnAttack(Point source, Point target, int distance) {
        if (checkRankLengthOne(source, target, distance) && checkFileLengthOne(source, target, distance)) {
            return true;
        }
        return checkRankLengthOne(source, target, distance) && checkFileLengthOne(source, target, -distance);
    }

    private boolean checkRankLengthOne(Point source, Point target, int distance) {
        return source.rankDistance(target) == distance;
    }

    private boolean checkFileLengthOne(Point source, Point target, int distance) {
        return source.fileDistance(target) == distance;
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
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public String toString() {
        return team.convertTeamName(ORIGINAL_NAME);
    }
}
