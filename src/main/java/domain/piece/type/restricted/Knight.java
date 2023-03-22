package domain.piece.type.restricted;

import java.util.List;

import domain.piece.Camp;
import domain.piece.type.Type;

public class Knight extends RestrictedPiece {
    public static final int KNIGHT_GAP_SUM = 3;
    public static final int KNIGHT_GAP_SUBTRACTION = 1;

    public Knight(Camp camp, Type type) {
        super(camp, type);
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        Integer fileGapAbs = Math.abs(gaps.get(FILE));
        Integer rankGapAbs = Math.abs(gaps.get(RANK));
        int gapsSum = fileGapAbs + rankGapAbs;
        int gapsSubtraction = Math.abs(fileGapAbs - rankGapAbs);
        if (gapsSum != KNIGHT_GAP_SUM || gapsSubtraction != KNIGHT_GAP_SUBTRACTION) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
