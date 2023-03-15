package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Rook implements Piece {
    private static final String ORIGINAL_NAME = "r";

    private final String name;

    private Rook(String name) {
        this.name = name;
    }

    public static Rook from(Team team) {
        return new Rook(team.calculate(ORIGINAL_NAME));
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isHorizontal(target) || source.isVertical(target);
    }
}
