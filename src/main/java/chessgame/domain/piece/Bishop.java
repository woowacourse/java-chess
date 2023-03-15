package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Bishop implements Piece {
    private static final String ORIGINAL_NAME = "b";

    private final String name;

    private Bishop(String name) {
        this.name = name;
    }

    public static Bishop from(Team team) {
        return new Bishop(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isDiagonal(target);
    }
}
