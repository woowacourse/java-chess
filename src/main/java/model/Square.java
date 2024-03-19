package model;

import point.Point;

public class Square {

    private final Point point;
    private final SquareInfo squareInfo;

    public Square(Point point, SquareInfo squareInfo) {
        this.point = point;
        this.squareInfo = squareInfo;
    }

    public SquareInfo getSquareInfo() {
        return squareInfo;
    }

    @Override
    public String toString() {
        return squareInfo.toString();
    }
}
