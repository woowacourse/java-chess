package chessgame.domain.piece;

import chessgame.domain.Team;
import chessgame.domain.point.Point;

public class Pawn implements Piece {
    private static final String ORIGINAL_NAME = "p";

    private final Team team;

    private Pawn(Team team) {
        this.team = team;
    }

    public static Pawn from(Team team) {
        return new Pawn(team);
    }

    public boolean isAttack(Point source, Point target){
        return source.isPawnAttack(target, team);
    }

    @Override
    public boolean isMovable(Point source, Point target) {
        return source.isPawnMove(target, team);
    }

    @Override
    public Team team() {
        return team;
    }

    @Override
    public String toString() {
        return team.calculate(ORIGINAL_NAME);
    }
}
