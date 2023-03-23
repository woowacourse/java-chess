package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Knight implements Piece {
    private static final String ORIGINAL_NAME = "n";
    public static final double KNIGHT_SCORE = 2.5;
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
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return isKnightMove(source, target);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
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
        return ORIGINAL_NAME;
    }
}
