package chess.domain.direction;

import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Way {
    private static final int INFINITY = -1;

    private Direction direction;
    private MoveStrategy moveStrategy;
    private int step;

    public Way(Direction direction, MoveStrategy moveStrategy) {
        this(direction, moveStrategy, INFINITY);
    }

    public Way(Direction direction, MoveStrategy moveStrategy, int step) {
        this.direction = direction;
        this.moveStrategy = moveStrategy;
        this.step = step;
    }

    private boolean isInfinity() {
        return step == INFINITY;
    }

    boolean canMove(Square source, Square target) {
        return isInfinity() && direction.checkDirection(source, target) ||
                direction.checkDirection(source, target) && direction.calculateStep(source, target) == step;
    }

    Route generateRoute(Square source, Square target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException();
        }

        return new Route(
                IntStream.rangeClosed(0, (int) direction.calculateStep(source, target))
                        .mapToObj(step -> direction.calculateSquare(source, step))
                        .collect(Collectors.toList())
                , moveStrategy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Way)) return false;
        Way way = (Way) o;
        return step == way.step &&
                direction == way.direction &&
                moveStrategy == way.moveStrategy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, moveStrategy, step);
    }
}
