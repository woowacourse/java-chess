package chess.domain.position;

public final class Position {
    private final PositionX positionX;
    private final PositionY positionY;

    public Position(PositionX positionX, PositionY positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public PositionX getPositionX() {
        return positionX;
    }

    public PositionY getPositionY() {
        return positionY;
    }
}
