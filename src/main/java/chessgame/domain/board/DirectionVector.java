package chessgame.domain.board;

import chessgame.domain.piece.Coordinate;

import java.util.Arrays;

public enum DirectionVector {

    N(new Coordinate(1, 0)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.isColZero();
        }
    },
    NE(new Coordinate(1, 1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.hasPositiveColValue();
        }
    },
    E(new Coordinate(0, 1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.isRowZero() && coordinate.hasPositiveColValue();
        }
    },
    SE(new Coordinate(-1, 1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.hasPositiveColValue();
        }
    },
    S(new Coordinate(-1, 0)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.isColZero();
        }
    },
    SW(new Coordinate(-1, -1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.hasNegativeColValue();
        }
    },
    W(new Coordinate(0, -1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.isRowZero() && coordinate.hasNegativeColValue();
        }
    },
    NW(new Coordinate(1, -1)) {
        @Override
        boolean isExist(Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.hasNegativeColValue();
        }
    };

    private final Coordinate directionVector;

    DirectionVector(final Coordinate directionVector) {
        this.directionVector = directionVector;
    }

    public static Coordinate calculate(Coordinate startCoordinate, Coordinate endCoordinate) {
        Coordinate minusCoordinate = startCoordinate.minus(endCoordinate);

        return Arrays.stream(DirectionVector.values())
                .filter(directionVector -> directionVector.isExist(minusCoordinate))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 방향을 찾을 수 없습니다."))
                .directionVector;
    }

    abstract boolean isExist(Coordinate coordinate);
}
