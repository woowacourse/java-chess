package chess.domain;

import java.util.Optional;
import java.util.function.BiFunction;

public enum Direction {
    UP((x, y) ->
        y.move(1)
            .map(movedY -> CoordinatePair.of(x, movedY))
    ), DOWN((x, y) ->
        y.move(-1)
            .map(movedY -> CoordinatePair.of(x, movedY))
    ), LEFT((x, y) ->
        x.move(-1)
            .map(movedX -> CoordinatePair.of(movedX, y))
    ), RIGHT((x, y) ->
        x.move(1)
            .map(movedX -> CoordinatePair.of(movedX, y))
    ), LEFT_TOP((x, y) ->
        CoordinatePair.of(x, y).move(UP)
            .map(coord -> coord.move(LEFT)
                .orElse(null))
    ), RIGHT_TOP((x, y) ->
        CoordinatePair.of(x, y).move(UP)
            .map(coord -> coord.move(RIGHT)
                .orElse(null))
    ), LEFT_BOTTOM((x, y) ->
        CoordinatePair.of(x, y).move(DOWN)
            .map(coord -> coord.move(LEFT)
                .orElse(null))
    ), RIGHT_BOTTOM((x, y) ->
        CoordinatePair.of(x, y).move(DOWN)
            .map(coord -> coord.move(RIGHT)
                .orElse(null)));

    private BiFunction<CoordinateX, CoordinateY, Optional<CoordinatePair>> movingStrategy;

    Direction(BiFunction<CoordinateX, CoordinateY, Optional<CoordinatePair>> movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public Optional<CoordinatePair> move(CoordinateX x, CoordinateY y) {
        return this.movingStrategy.apply(x, y);
    }
}
