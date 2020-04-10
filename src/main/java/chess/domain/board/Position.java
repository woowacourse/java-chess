package chess.domain.board;

import chess.domain.piece.direction.Direction;

import java.util.Objects;

import static chess.util.NullValidator.validateNull;

public class Position {
    private Xpoint xPoint;
    private Ypoint yPoint;

    public Position(Xpoint xPoint, Ypoint yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public Position(int xPoint, int yPoint) {
        this.xPoint = Xpoint.of(xPoint);
        this.yPoint = Ypoint.of(yPoint);
    }

    public Position(String position) {
        validateNull(position);
        this.xPoint = Xpoint.of(position.charAt(0) - 'a' + 1);
        this.yPoint = Ypoint.of(Integer.parseInt(String.valueOf(position.charAt(1))));
    }

    private int getXPointGap(Position targetPosition) {
        return targetPosition.xPoint.getGapValue(this.xPoint);
    }

    private int getYPointGap(Position targetPosition) {
        return targetPosition.yPoint.getGapValue(this.yPoint);
    }

    public int getXPointDirectionValueTo(Position targetPosition) {
        int xPointGap = getXPointGap(targetPosition);
        if (xPointGap == 0) {
            return xPointGap;
        }
        return xPointGap / Math.abs(xPointGap);
    }

    public int getYPointDirectionValueTo(Position targetPosition) {
        int yPointGap = getYPointGap(targetPosition);
        if (yPointGap == 0) {
            return yPointGap;
        }
        return yPointGap / Math.abs(yPointGap);
    }

    public boolean isDifferentXPoint(Position position) {
        return this.xPoint != position.xPoint;
    }

    public boolean isDifferentYPoint(Position position) {
        return this.yPoint != position.yPoint;
    }

    public boolean isYPointEqualsTwo() {
        return this.yPoint.isTwo();
    }

    public boolean isYPointEqualsSeven() {
        return this.yPoint.isSeven();
    }

    public Position changeTo(Direction direction) {
        validateNull(direction);

        int changedXPoint = this.xPoint.getValue() + direction.getXPointValue();
        int changedYPoint = this.yPoint.getValue() + direction.getYPointValue();

        return PositionFactory.of(changedXPoint, changedYPoint);
    }

    public String getName() {
        return this.xPoint.getName() + this.yPoint.getName();
    }

    public boolean isNotSameXYGapWith(Position targetPosition) {
        return Math.abs(getXPointGap(targetPosition)) != Math.abs(getYPointGap(targetPosition));
    }

    public boolean hasXGap(Position targetPosition, int distance) {
        return getXPointGap(targetPosition) == distance;
    }

    public boolean hasYGap(Position targetPosition, int distance) {
        return getYPointGap(targetPosition) == distance;
    }

    public boolean isBiggerGapWith(Position targetPosition, int distance) {
        return distance < Math.abs(getXPointGap(targetPosition)) || distance < Math.abs(getYPointGap(targetPosition));
    }

    public int getXYGapSum(Position targetPosition) {
        return Math.abs(getXPointGap(targetPosition)) + Math.abs(getYPointGap(targetPosition));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPoint == position.xPoint &&
                yPoint == position.yPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPoint, yPoint);
    }

    public int getPointSum() {
        return this.xPoint.getValue() + this.yPoint.getValue();
    }
}
