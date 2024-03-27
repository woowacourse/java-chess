package domain.piece;

import domain.position.Direction;
import domain.position.Position;
import java.util.Set;

public class King extends Piece {

    private static final Set<Direction> VALID_DIRECTIONS = Direction.allDirections();
    private static final int ZERO_STEP = 0;
    private static final int ONE_STEP = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        Direction direction = Direction.asDirection(source, target);
        return VALID_DIRECTIONS.contains(direction)
                && source.isLegalRankStep(target, ZERO_STEP, ONE_STEP)
                && source.isLegalFileStep(target, ZERO_STEP, ONE_STEP);
    }
}
