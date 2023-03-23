package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Rook implements Piece {
    private static final String ORIGINAL_NAME = "r";
    public static final double ROOK_SCORE = 5;

    private final Team team;

    private Rook(Team team) {
        this.team = team;
    }

    public static Rook from(Team team) {
        return new Rook(team);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public boolean isMovable(Point source, Point target, boolean hasTarget) {
        return source.isHorizontal(target) || source.isVertical(target);
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
        return ROOK_SCORE;
    }

    @Override
    public String toString() {
        return ORIGINAL_NAME;
    }
}
