package domain.piece;

import domain.game.Direction;
import domain.position.Position;

public enum Movable {

    UP(Direction.NORTH),
    DOWN(Direction.SOUTH),
    LEFT(Direction.WEST),
    RIGHT(Direction.EAST);

    private final Direction direction;

    Movable(Direction direction) {
        this.direction = direction;
    }

    public boolean canMove(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        return this.direction == direction;
    }

}
