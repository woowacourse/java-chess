package domain.piece.type.unrestricted;

import java.util.List;

import domain.piece.Camp;
import domain.piece.type.Type;

public class Bishop extends UnrestrictedPiece {
    public Bishop(Camp camp, Type type) {
        super(camp, type);
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        if (isNotDiagonal(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
