package chess.domain.direction;

import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Way {
    private static final int INFINITY = -1;

    private Direction direction;
    private int step;

    public Way(Direction direction) {
        this.direction = direction;
        this.step = INFINITY;
    }

    public Way(Direction direction, int step) {
        this.direction = direction;
        this.step = step;
    }

    Square move(Square startSquare) {
        return move(startSquare, 0);
    }

    private Square move(Square currentSquare, int count) {
        if (count == this.step) {
            return currentSquare;
        }
        return move(direction.move(currentSquare), count++);
    }

    boolean isGo(Square source, Square target) {
        return isInfinity() && direction.checkDirection(source, target) ||
            direction.checkDirection(source, target) && direction.calculateStep(source, target) == step;
    }

    private boolean isInfinity() {
        return step == INFINITY;
    }

    Route generateRoute(Square source, Square target) {
        if(!isGo(source, target)){
            throw new IllegalArgumentException("ss");
        }
        List<Square> squares = new ArrayList<>();
        squares.add(source);
        for (int i = 1; i <= direction.calculateStep(source, target); i++) {
            Square prevSquare = squares.get(i-1);
            squares.add(direction.move(prevSquare));
        }
        return new Route(squares);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Way way = (Way) o;
        return step == way.step &&
                direction == way.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, step);
    }
}
