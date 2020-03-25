package chess.domain.movefactory;

import chess.domain.Position;

public class Straight implements MoveType {
    private final Direction direction;
    private final int count;

    public Straight(Position source, Position target) {
        this.direction = Direction.UP;
        this.count = 2;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
