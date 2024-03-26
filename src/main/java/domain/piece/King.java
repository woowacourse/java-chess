package domain.piece;

import domain.position.Movement;
import domain.position.Position;
import java.util.Set;

public class King extends Piece {

    private static final Set<Movement> VALID_MOVEMENTS = Movement.allMovements();
    private static final int ZERO_STEP = 0;
    private static final int ONE_STEP = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        return VALID_MOVEMENTS.contains(movement)
                && source.isLegalRankStep(target, ZERO_STEP, ONE_STEP)
                && source.isLegalFileStep(target, ZERO_STEP, ONE_STEP);
    }
}
