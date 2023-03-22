package chess.domain.move;

import static chess.domain.move.Axis.HORIZON;
import static chess.domain.move.Axis.VERTICAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.domain.position.Position;

public class UnitMove {

    private final Directions directions;

    UnitMove(Directions unitDirections) {
        validateNotEmpty(unitDirections);
        validateSingleUnit(unitDirections);
        this.directions = unitDirections;
    }

    public static UnitMove of(Directions anyDirections) {
        validateNotEmpty(anyDirections);
        Directions horizontals = getDirectionsOf(HORIZON, anyDirections);
        Directions verticals = getDirectionsOf(VERTICAL, anyDirections);
        return new UnitMove(horizontals.join(verticals));
    }

    private static Directions getDirectionsOf(Axis axis, Directions directions) {
        long minimumDirectionCount = directions.countDirectionsIn(axis) / directions.countMinimumUnits();
        return directions.getDirectionOf(axis)
                .map(direction -> direction.repeat(minimumDirectionCount))
                .map(Directions::new)
                .orElse(Directions.NONE);
    }

    private static void validateNotEmpty(Directions directions) {
        if (directions.isEmpty()) {
            throw new IllegalArgumentException("방향이 존재해야합니다");
        }
    }

    private void validateSingleUnit(Directions directions) {
        if (directions.countMinimumUnits() > 1) {
            throw new IllegalArgumentException("한 단위가 아닙니다");
        }
    }

    public Position move(Position position) {
        return directions.move(position);
    }

    public List<UnitMove> repeat(long times) {
        List<UnitMove> unitMoves = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            unitMoves.add(this);
        }
        return unitMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UnitMove unitMove = (UnitMove)o;

        return Objects.equals(directions, unitMove.directions);
    }

    @Override
    public int hashCode() {
        return directions != null ? directions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return directions.toString();
    }
}