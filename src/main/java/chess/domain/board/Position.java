package chess.domain.board;

import java.util.Objects;

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
