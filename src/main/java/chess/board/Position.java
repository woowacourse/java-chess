package chess.board;

import java.util.Objects;

public class Position {

    private XPosition xPosition;
    private YPosition yPosition;

    public Position(XPosition xPosition, YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public XPosition getXPosition() {
        return this.xPosition;
    }

    public YPosition getYPosition() {
        return this.yPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPosition == position.xPosition && yPosition == position.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}
