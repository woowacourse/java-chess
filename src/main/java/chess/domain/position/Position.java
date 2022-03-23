package chess.domain.position;

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
}
