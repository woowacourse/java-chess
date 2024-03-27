package domain.piece.pawn;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public class BlackPawn extends Pawn {

    private static final int FIRST_POSITION = 7;

    public BlackPawn() {
        super(Color.BLACK, Direction.BLACK_PAWN_DIRECTION);
    }

    @Override
    public boolean isFirstPosition(Coordinate coordinate) {
        return coordinate.isSameRowPosition(FIRST_POSITION);
    }
}
