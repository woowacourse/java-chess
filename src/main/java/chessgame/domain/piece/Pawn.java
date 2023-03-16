package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";

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
            if (source.rankDistance(target) == 1 && source.fileDistance(target) == 1) {
                return true;
            }
            if (source.rankDistance(target) == 1 && source.fileDistance(target) == -1) {
                return true;
            }
        }
        if (team == Team.WHITE) {
            if (source.rankDistance(target) == -1 && source.fileDistance(target) == -1) {
                return true;
            }
            if (source.rankDistance(target) == -1 && source.fileDistance(target) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return isPawnMove(source, target, team);
    }

    public boolean isPawnMove(Point source, Point target, Team team) {
        if (team == Team.BLACK && source.fileDistance(target) == 0) {
            if (source.isInitialPoint(Rank.SEVEN) && (source.rankDistance(target) == 1
                || source.rankDistance(target) == 2)) {
                return true;
            }
            if (source.rankDistance(target) == 1) {
                return true;
            }
        }
        if (team == Team.WHITE && source.fileDistance(target) == 0) {
            if (source.isInitialPoint(Rank.TWO) && (source.rankDistance(target) == -1
                || source.rankDistance(target) == -2)) {
                return true;
            }
            if (source.rankDistance(target) == -1) {
                return true;
            }
        }
        return false;
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
