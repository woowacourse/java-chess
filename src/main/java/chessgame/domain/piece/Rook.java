package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Rook implements Piece {
    private static final String ORIGINAL_NAME = "r";

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

    public String toString() {
        return team.convertTeamName(ORIGINAL_NAME);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isHorizontal(target) || source.isVertical(target);
    }
}
