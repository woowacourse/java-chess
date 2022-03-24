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
        return position.getPositionX().getCoordination() - positionX.getCoordination();
    }

    public int calculateDistanceY(Position position) {
        return position.getPositionY().getCoordination() - positionY.getCoordination();
    }

    public PositionX getPositionX() {
        return positionX;
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
