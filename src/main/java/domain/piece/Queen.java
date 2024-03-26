package domain.piece;

import domain.position.Movement;
import domain.position.Position;
import java.util.Set;

public class Queen extends Piece {

    private static final Set<Movement> VALID_MOVEMENTS = Movement.allMovements();

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        return VALID_MOVEMENTS.contains(movement);
    }
}
