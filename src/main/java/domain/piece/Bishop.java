package domain.piece;

import static domain.position.Direction.LEFT_DOWN;
import static domain.position.Direction.LEFT_UP;
import static domain.position.Direction.RIGHT_DOWN;
import static domain.position.Direction.RIGHT_UP;

import domain.position.Direction;
import domain.position.Position;
import java.util.Set;

public class Bishop extends Piece {

    private static final Set<Direction> VALID_DIRECTIONS = Set.of(RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.asDirection(source, target);
        return VALID_DIRECTIONS.contains(direction);
    }
}
