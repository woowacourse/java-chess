package chess.domain;

import java.util.Optional;
import java.util.function.Function;

public enum Direction {
    UP(coordinatePair ->
        coordinatePair.getY().move(1)
            .map(y -> CoordinatePair.of(coordinatePair.getX(), y))
    ), DOWN(coordinatePair ->
        coordinatePair.getY().move(-1)
            .map(y -> CoordinatePair.of(coordinatePair.getX(), y))
    ), LEFT(coordinatePair ->
        coordinatePair.getX().move(-1)
            .map(x -> CoordinatePair.of(x, coordinatePair.getY()))
    ), RIGHT(coordinatePair ->
        coordinatePair.getX().move(1)
            .map(x -> CoordinatePair.of(x, coordinatePair.getY()))
    ), LEFT_TOP(coordinatePair ->
        UP.move(coordinatePair)
            .map(coord -> LEFT.move(coord)
                .orElse(null))
    ), RIGHT_TOP(coordinatePair ->
        UP.move(coordinatePair)
            .map(coord -> RIGHT.move(coord)
                .orElse(null))
    ), LEFT_BOTTOM(coordinatePair ->
        DOWN.move(coordinatePair)
            .map(coord -> LEFT.move(coord)
                .orElse(null))
    ), RIGHT_BOTTOM(coordinatePair ->
        DOWN.move(coordinatePair)
            .map(coord -> RIGHT.move(coord)
                .orElse(null))
    );

    private Function<CoordinatePair, Optional<CoordinatePair>> movingStrategy;

    Direction(Function<CoordinatePair, Optional<CoordinatePair>> movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public Optional<CoordinatePair> move(CoordinatePair from) {
        return this.movingStrategy.apply(from);
    }
}
