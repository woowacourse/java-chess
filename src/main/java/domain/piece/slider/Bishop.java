package domain.piece.slider;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;

public class Bishop extends Slider {
    private static final List<Direction> MOVABLE_DIRECTIONS = List.of(Direction.NORTH_EAST, Direction.NORTH_WEST,
        Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public Bishop(Camp camp) {
        super(camp, MOVABLE_DIRECTIONS);
    }

    @Override
    protected void validateDistance(Square currentSquare, Square targetSquare) {
    }
}
