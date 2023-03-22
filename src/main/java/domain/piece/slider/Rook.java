package domain.piece.slider;

import java.util.List;

import domain.piece.Camp;
import domain.piece.Direction;

public class Rook extends Slider {
    private static final List<Direction> MOVABLE_DIRECTIONS = List.of(Direction.EAST, Direction.WEST,
        Direction.SOUTH, Direction.NORTH);

    public Rook(Camp camp) {
        super(camp, MOVABLE_DIRECTIONS);
    }
}
