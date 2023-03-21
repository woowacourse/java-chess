package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Bishop implements Piece {
    private static final String ORIGINAL_NAME = "b";

    private final Team team;

    private Bishop(Team team) {
        this.team = team;
    }

    public static Bishop from(Team team) {
        return new Bishop(team);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
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
    public String toString() {
        return team.convertTeamName(ORIGINAL_NAME);
    }
}
