package chess.domain.move;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.domain.position.Position;

public class Move {

    private final List<UnitMove> unitMoves;

    public Move(List<UnitMove> unitMoves) {
        this.unitMoves = unitMoves;
    }

    public Move(Directions directions) {
        this(UnitMove.of(directions).repeat(directions.countMinimumUnits()));
    }

    public Move(Direction... directions) {
        this(new Directions(List.of(directions)));
    }

    public static Move of(Position source, Position target) {
        Directions verticalDirections = source.getVerticalDirectionsTo(target);
        Directions horizontalDirections = source.getHorizontalDirectionsTo(target);
        return new Move(verticalDirections.join(horizontalDirections));
    }

    public Position move(Position position) {
        Position destination = position;
        for (UnitMove unitMove : unitMoves) {
            destination = unitMove.move(destination);
        }
        return destination;
    }

    public Move getUnitMove() {
        return new Move(unitMoves.subList(0, 1));
    }

    public Move repeat(int times) {
        List<UnitMove> repeatedUnitMoves = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            repeatedUnitMoves.addAll(unitMoves);
        }
        return new Move(repeatedUnitMoves);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Move move = (Move)o;

        return Objects.equals(unitMoves, move.unitMoves);
    }

    @Override
    public int hashCode() {
        return unitMoves != null ? unitMoves.hashCode() : 0;
    }

    @Override
    public String toString() {
        return unitMoves.toString();
    }
}
