package chess.domain.chess.unit;

import chess.domain.chess.game.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;

import java.util.List;

public class Pawn extends Unit {
    private static List<Direction> whiteDirections;
    private static List<Direction> blackDirections;

    static {
        whiteDirections = Direction.northDirections();
        blackDirections = Direction.southDirections();
    }

    public Pawn(Team team) {
        super(team, UnitInformation.PAWN.name());
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return false;
    }

    public boolean validateDirection(Position source, Position target, boolean isEnemyPresent) {
        Vector vector = Vector.of(source, target);

        if (vector.validateDistance(2)) {
            return source.getY() == 1 || source.getY() == 6;
        }

        if (validateDirection(whiteDirections, vector) && getTeam().equals(Team.WHITE)) {
            return isEnemyPresent ? validateDirection(Direction.northDiagonal(), vector)
                    : Direction.NORTH.isEqualTo(vector);
        }

        if (validateDirection(blackDirections, vector) && getTeam().equals(Team.BLACK)) {
            return isEnemyPresent ? validateDirection(Direction.southDiagonal(), vector)
                    : Direction.SOUTH.isEqualTo(vector);
        }

        return false;
    }

    private boolean validateDirection(List<Direction> directions, Vector vector) {
        return directions.stream()
                .anyMatch(direction -> direction.isEqualTo(vector));
    }

    @Override
    public String toString() {
        return getTeam().applyUpperCaseOrLowerCaseByTeam(UnitInformation.PAWN.symbol());
    }
}

