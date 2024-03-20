package domain.piece;

import domain.game.Direction;
import domain.position.Position;

public class Movable {
    private final int maxMovement;
    private final Direction direction;

    public Movable(int maxMovement, Direction direction) {
        this.maxMovement = maxMovement;
        this.direction = direction;
    }

    public boolean canMove(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        return this.direction == direction;
    }

}
