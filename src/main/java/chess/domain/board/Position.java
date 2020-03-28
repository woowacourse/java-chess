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

    public int getXPointDirectionValueTo(Position targetPosition) {
        int xPointGap = targetPosition.xPoint.getGapValue(this.xPoint);
        if (xPointGap == 0) {
            return xPointGap;
        }
        return xPointGap / Math.abs(xPointGap);
    }

    public int getYPointDirectionValueTo(Position targetPosition) {
        int yPointGap = targetPosition.yPoint.getGapValue(this.yPoint);
        if (yPointGap == 0) {
            return yPointGap;
        }
        return yPointGap / Math.abs(yPointGap);
    }

    public void plus(Direction direction) {
        validateNull(direction);

        this.xPoint = Xpoint.of(direction.getXPoint() + this.xPoint.getValue());
        this.yPoint = Ypoint.of(direction.getYPoint() + this.yPoint.getValue());
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
}
