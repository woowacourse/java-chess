package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";

    private final String name;

    private Pawn(String name) {
        this.name = name;
    }

    public static Pawn from(Team team) {
        return new Pawn(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isPawnMove(target, name.charAt(0)) || source.isPawnAttack(target, name.charAt(0));
    }

    @Override
    public String toString() {
        return name;
    }
}
