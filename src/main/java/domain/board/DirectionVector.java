package domain.board;

import domain.piece.Coordinate;

import java.util.Arrays;

public enum DirectionVector {

    N(new Coordinate(1, 0)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() > 0 && coordinate.getCol() == 0;
        }
    },
    NE(new Coordinate(1, 1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() > 0 && coordinate.getCol() > 0;
        }
    },
    E(new Coordinate(0, 1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() == 0 && coordinate.getCol() > 0;
        }
    },
    SE(new Coordinate(-1, 1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() < 0 && coordinate.getCol() > 0;
        }
    },
    S(new Coordinate(-1, 0)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() < 0 && coordinate.getCol() == 0;
        }
    },
    SW(new Coordinate(-1, -1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() < 0 && coordinate.getCol() < 0;
        }
    },
    W(new Coordinate(0, -1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() == 0 && coordinate.getCol() < 0;
        }
    },
    NW(new Coordinate(1, -1)) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.getRow() > 0 && coordinate.getCol() < 0;
        }
    };

    private final Coordinate directionVector;

    DirectionVector(final Coordinate directionVector) {
        this.directionVector = directionVector;
    }

    public static Coordinate calculate(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Coordinate minusCoordinate = startCoordinate.minus(endCoordinate);

        return Arrays.stream(DirectionVector.values())
                .filter(directionVector -> directionVector.isExist(minusCoordinate))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 방향을 찾을 수 없습니다."))
                .directionVector;
    }

    abstract boolean isExist(final Coordinate coordinate);
}
