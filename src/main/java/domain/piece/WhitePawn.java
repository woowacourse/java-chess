package domain.piece;

import domain.coordinate.Coordinate;
import domain.coordinate.Position;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.PawnBase;

public class WhitePawn extends PawnBase {

    private static final int WHITE_PAWN_INITIAL_ROW = 6;

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    public boolean isInitialPawn(Coordinate start) {
        return start.hasSameRowPosition(Position.of(WHITE_PAWN_INITIAL_ROW));
    }

    @Override
    public boolean canMove(Direction direction, int distance, boolean isInitialPawn) {
        if (direction == StraightDirection.UP) {
            return isMovableDistance(distance, isInitialPawn);
        }
        return direction == DiagonalDirection.UP_RIGHT || direction == DiagonalDirection.UP_LEFT;
    }
}
