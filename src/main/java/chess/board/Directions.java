package chess.board;

import chess.coordinate.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Directions {
    private final Queue<Direction> directions;

    public Directions(final List<Direction> directions) {
        this.directions = new LinkedList<>(directions);
        this.directions.poll();
    }

    public boolean isNotEmpty() {
        return !this.directions.isEmpty();
    }

    public Direction poll() {
        return this.directions.poll();
    }
}
