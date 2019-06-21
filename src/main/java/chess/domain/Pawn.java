package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Unit {
    private static List<Direction> whitePawnAttack = new ArrayList<>();
    private static List<Direction> whitePawnMove = new ArrayList<>();

    private static List<Direction> blackPawnAttack = new ArrayList<>();
    private static List<Direction> blackPawnMove = new ArrayList<>();

    static {
        whitePawnAttack.add(Direction.NORTHEAST);
        whitePawnAttack.add(Direction.NORTHWEST);
        whitePawnMove.add(Direction.NORTH);
        blackPawnAttack.add(Direction.SOUTHWEST);
        blackPawnAttack.add(Direction.SOUTHEAST);
        blackPawnMove.add(Direction.SOUTH);
    }

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return false;
    }

    public boolean validateDirection(Position source, Position target, boolean isEnemyPresent) {
        Vector vector = Vector.of(source, target);

        if (vector.validateDistance(2)) {
            if (source.getY() == 1 || source.getY() == 6) {
                return true;
            }
        }

        if (isEnemyPresent == false) {
            if (getTeam() == Team.WHITE) {
                return whitePawnMove.stream()
                        .anyMatch(direction -> direction.isEqualTo(vector));
            }
            return blackPawnMove.stream()
                    .anyMatch(direction -> direction.isEqualTo(vector));
        }

        if (isEnemyPresent) {
            if (getTeam() == Team.WHITE) {
                return whitePawnAttack.stream()
                        .anyMatch(direction -> direction.isEqualTo(vector));
            }
            return blackPawnAttack.stream()
                    .anyMatch(direction -> direction.isEqualTo(vector));
        }

        return false;
    }
}

