package domain.piece.pawn;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public class WhitePawn extends Pawn {

    private static final int FIRST_POSITION = 2;

    public WhitePawn() {
        super(Color.WHITE, Direction.WHITE_PAWN_DIRECTION);
    }

    @Override
    public boolean isFirstPosition(Coordinate coordinate) {
        return coordinate.isSameRowPosition(FIRST_POSITION);
    }
}
