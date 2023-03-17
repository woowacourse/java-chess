package domain.piece.type;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Knight extends Piece {
    public static final int KNIGHT_GAP_SUM = 3;
    public static final int KNIGHT_GAP_SUBTRACTION = 1;

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKnight() {
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
        Integer fileGapAbs = Math.abs(gaps.get(FILE));
        Integer rankGapAbs = Math.abs(gaps.get(RANK));
        int gapsSum = fileGapAbs + rankGapAbs;
        int gapsSubtraction = Math.abs(fileGapAbs - rankGapAbs);
        if(gapsSum != KNIGHT_GAP_SUM || gapsSubtraction != KNIGHT_GAP_SUBTRACTION) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
