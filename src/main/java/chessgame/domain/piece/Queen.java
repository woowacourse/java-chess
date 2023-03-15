package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Queen implements Piece {
    private static final String ORIGINAL_NAME = "q";

    private final String name;

    private Queen(String name) {
        this.name = name;
    }

    public static Queen from(Team team) {
        return new Queen(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isHorizontal(target) || source.isVertical(target) || source.isDiagonal(target);
    }

    @Override
    public String toString() {
        return name;
    }
}
