package chess.domain.move;

import java.util.List;
import java.util.Objects;

import chess.domain.position.Position;

public class Move {

    private final Directions directions;

    public Move(Directions directions) {
        validateNotEmpty(directions);
        this.directions = directions;
    }

    public Move(Direction... directions) {
        this(new Directions(List.of(directions)));
    }

    public static Move of(Position source, Position target) {
        return new Move(source.getDirectionsTo(target));
    }

    private void validateNotEmpty(Directions directions) {
        if (directions.isEmpty()) {
            throw new IllegalArgumentException("방향이 존재해야합니다");
        }
    }

    public Position move(Position position) {
        return directions.move(position);
    }

    public Move getUnitMove() {
        return new Move(directions.splitIntoMinimumUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Move move = (Move)o;

        return Objects.equals(directions, move.directions);
    }

    @Override
    public int hashCode() {
        return directions != null ? directions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Move{" +
                "directions=" + directions +
                '}';
    }
}
