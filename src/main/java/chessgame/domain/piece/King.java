package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class King implements Piece {
    private static final String ORIGINAL_NAME = "k";

    private final Team team;

    private King(Team team) {
        this.team = team;
    }

    public static King from(Team team) {
        return new King(team);
    }

    @Override
    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isAllDirectionOneDistance(target);
    }

    @Override
    public Team team() {
        return team;
    }
}
