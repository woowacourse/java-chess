package domain.piece.slider;

import java.util.Arrays;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;

public class King extends Slider {
    private static final List<Direction> MOVABLE_DIRECTIONS = Arrays.asList(Direction.values());
    private static final int MOVABLE_DISTANCE = 1;

    public King(Camp camp) {
        super(camp, MOVABLE_DIRECTIONS);
    }

    @Override
    public List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare) {
        int fileGap = fetchGap(currentSquare, targetSquare, FILE_INDEX);
        int rankGap = fetchGap(currentSquare, targetSquare, RANK_INDEX);
        if (Math.abs(fileGap) > MOVABLE_DISTANCE || Math.abs(rankGap) > MOVABLE_DISTANCE) {
            throw new IllegalArgumentException("King은 두 칸 이상 이동할 수 없습니다.");
        }
        return super.fetchMovableSquares(currentSquare, targetSquare);
    }
}
