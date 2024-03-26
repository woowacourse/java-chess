package domain.piece;

import static domain.position.Movement.DOWN;
import static domain.position.Movement.LEFT;
import static domain.position.Movement.RIGHT;
import static domain.position.Movement.UP;

import domain.position.Movement;
import domain.position.Position;
import java.util.Set;

public class Rook extends Piece {

    private static final Set<Movement> VALID_MOVEMENTS = Set.of(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        return VALID_MOVEMENTS.contains(movement);
    }
}
