package domain.piece.slider;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;

public class Rook extends Slider {
    private static final List<Direction> MOVABLE_DIRECTIONS = List.of(Direction.EAST, Direction.WEST,
        Direction.SOUTH, Direction.NORTH);

    public Rook(Camp camp) {
        super(camp, MOVABLE_DIRECTIONS);
    }

    @Override
    protected void validateDistance(Square currentSquare, Square targetSquare) {
    }
}
