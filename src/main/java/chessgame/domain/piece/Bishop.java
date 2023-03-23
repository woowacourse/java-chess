package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Bishop implements Piece {
    private static final String ORIGINAL_NAME = "b";
    public static final double BISHOP_SCORE = 3;
    private final Team team;

    private Bishop(Team team) {
        this.team = team;
    }

    public static Bishop from(Team team) {
        return new Bishop(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return source.isDiagonal(target);
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
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public String toString() {
        return ORIGINAL_NAME;
    }
}
