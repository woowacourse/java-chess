package domain.piece;

import domain.coordinate.Coordinate;
import domain.coordinate.Position;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.PawnBase;

public class BlackPawn extends PawnBase {

    private static final int BLACK_PAWN_INITIAL_ROW = 1;

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    public boolean isInitialPawn(Coordinate start) {
        return start.hasSameRowPosition(Position.of(BLACK_PAWN_INITIAL_ROW));
    }

    @Override
    public boolean canMove(Direction direction, int distance, boolean isInitialPawn) {
        if (direction == StraightDirection.DOWN) {
            return isMovableDistance(distance, isInitialPawn);
        }
        return direction == DiagonalDirection.DOWN_RIGHT || direction == DiagonalDirection.DOWN_LEFT;
    }
}
