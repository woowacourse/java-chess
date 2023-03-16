package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Knight implements Piece {
    private static final String ORIGINAL_NAME = "n";

    private final Team team;

    private Knight(Team team) {
        this.team = team;
    }

    public static Knight from(Team team) {
        return new Knight(team);
    }

    @Override
    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isKnightMove(target);
    }
}
