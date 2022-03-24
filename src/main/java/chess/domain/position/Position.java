package chess.domain.position;

import java.util.Objects;

public final class Position {
    private final PositionX positionX;
    private final PositionY positionY;

    public Position(PositionX positionX, PositionY positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int calculateDistanceX(Position position) {
        return positionX.distanceFrom(position.positionX);
    }

    public int calculateDistanceY(Position position) {
        return positionY.distanceFrom(position.positionY);
    }

    public int calculateDistanceFrom(Position position) {
        return Math.max(Math.abs(calculateDistanceY(position)), Math.abs(calculateDistanceX(position)));
    }

    public int calculateXSlope(Position target, int routeLength) {
        return calculateDistanceX(target) / routeLength;
    }

    public int calculateYSlope(Position target, int routeLength) {
        return calculateDistanceY(target) / routeLength;
    }

    public Position shift(int xShift, int yShift) {
        return new Position(positionX.shift(xShift), positionY.shift(yShift));
    }

    public PositionY getPositionY() {
        return positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionX == position.positionX && positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
