package chess.domain.board;

public class MovingOrder {
    private final Position from;
    private final Position to;

    public MovingOrder(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public boolean isSameDirection(Direction direction) {
        return from.getXDistance(to) == direction.getX() &&
                from.getYDistance(to) == direction.getY();
    }
}
