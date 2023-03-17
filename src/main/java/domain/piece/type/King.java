package domain.piece.type;

import java.util.Collections;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class King extends Piece {
    public static final int MOVE_DISTANCE_LIMIT = 1;

    public King(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> gaps = calculateGap(currentSquare, targetSquare);
        validateMovable(gaps);
        return List.of(targetSquare);
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        Integer distance = Collections.max(gaps);
        if(distance != MOVE_DISTANCE_LIMIT) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
