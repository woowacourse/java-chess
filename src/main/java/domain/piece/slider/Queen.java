package domain.piece.slider;

import java.util.Arrays;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;

public class Queen extends Slider {
    private static final List<Direction> MOVABLE_DIRECTIONS = Arrays.asList(Direction.values());

    public Queen(Camp camp) {
        super(camp, MOVABLE_DIRECTIONS);
    }

    @Override
    protected void validateDistance(Square currentSquare, Square targetSquare) {
    }
}
