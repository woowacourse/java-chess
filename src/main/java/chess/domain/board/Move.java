package chess.domain.board;

import chess.domain.location.Location;
import java.util.List;
import java.util.Objects;

public class Move {

    private final List<Direction> directions;

    public Move(Direction... directions) {
        this(List.of(directions));
    }

    public Move(List<Direction> directions) {
        this.directions = directions;
    }

    public static Move of(Location source, Location target) {
        int verticalDistance = source.calculateVerticalDistance(target);
        int horizontalDistance = source.calculateHorizontalDistance(target);
        return new Move(Direction.createDirections(verticalDistance, horizontalDistance));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move move = (Move) o;
        //TODO 순서 무시 구현
        return Objects.equals(directions, move.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directions);
    }
}
