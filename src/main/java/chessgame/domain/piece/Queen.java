package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Queen implements Piece {
    private static final String ORIGINAL_NAME = "q";
    public static final double QUEEN_SCORE = 9;

    private final Team team;

    private Queen(Team team) {
        this.team = team;
    }

    public static Queen from(Team team) {
        return new Queen(team);
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return source.isHorizontal(target) || source.isVertical(target) || source.isDiagonal(target);
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
        return QUEEN_SCORE;
    }

    @Override
    public String toString() {
        return ORIGINAL_NAME;
    }
}
