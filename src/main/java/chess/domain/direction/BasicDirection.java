package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.position.Position;
import chess.domain.position.UnitPosition;

public enum BasicDirection implements Direction {

    SOUTH((rowDifference, columnDifference) ->
            rowDifference > 0 && columnDifference == 0, new UnitPosition(-1, 0)
    ),
    NORTH((rowDifference, columnDifference) ->
            rowDifference < 0 && columnDifference == 0, new UnitPosition(1, 0)
    ),
    WEST((rowDifference, columnDifference) ->
            rowDifference == 0 && columnDifference > 0, new UnitPosition(0, -1)
    ),
    EAST((rowDifference, columnDifference) ->
            rowDifference == 0 && columnDifference < 0, new UnitPosition(0, 1)
    );

    private final BiPredicate<Integer, Integer> directionPredicate;
    private final UnitPosition unitPosition;

    BasicDirection(BiPredicate<Integer, Integer> directionPredicate, UnitPosition unitPosition) {
        this.directionPredicate = directionPredicate;
        this.unitPosition = unitPosition;
    }

    @Override
    public boolean confirm(Position from, Position to) {
        return this.directionPredicate.test(from.subtractRow(to), from.subtractColumn(to));
    }

    @Override
    public boolean isDiagonal() {
        return false;
    }

    @Override
    public UnitPosition getUnitPosition() {
        return unitPosition;
    }
}
