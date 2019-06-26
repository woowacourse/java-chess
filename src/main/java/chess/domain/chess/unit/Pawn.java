package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.chess.exception.IllegalMovingRuleException;
import chess.domain.chess.exception.IllegalPawnMovingRuleException;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;

import java.util.List;

public class Pawn extends Unit {
    private static final double SCORE = 1;
    private static final double FIRST_MOVE_DISTANCE = 2;
    private static final double ONE_ROW = 1;
    private static final double SIX_ROW = 6;
    private static final String NAME = "Pawn";
    private static final String SYMBOL = "P";

    private static List<Direction> whiteDirections;
    private static List<Direction> blackDirections;

    static {
        whiteDirections = Direction.northDirections();
        blackDirections = Direction.southDirections();
    }

    public Pawn(Team team) {
        super(team, NAME);
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return false;
    }

    public boolean validateDirection(Position source, Position target, boolean isEnemyPresent) {
        Vector vector = Vector.of(source, target);

        if (vector.validateDistance(FIRST_MOVE_DISTANCE)) {
            return source.getY() == ONE_ROW || source.getY() == SIX_ROW;
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
    public double score() {
        return SCORE;
    }

    @Override
    public String toString() {
        return getTeam().getUnitName(SYMBOL);
    }
}

