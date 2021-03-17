package chess;

public interface MovingStrategy {

    default boolean canMove(Point source, Point destination) {
        return false;
    }
}
