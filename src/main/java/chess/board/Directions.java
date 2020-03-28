package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class Directions {
    private final Queue<Direction> directions;

    public Directions(final List<Direction> directions) {
        this.directions = new LinkedList<>(directions);
        this.directions.poll();
    }

    public boolean isNotEmpty() {
        return !this.directions.isEmpty();
    }

    public boolean isExist(String sourceKey, Function<Coordinate, Tile> tileFinder) {
        Coordinate next = Coordinate.of(sourceKey);
        boolean exist = false;
        while (isNotEmpty() && !exist) {
            Direction direction = directions.poll();
            next = next.move(direction);
            exist = !tileFinder.apply(next).isBlank();
        }

        return exist;
    }
}
