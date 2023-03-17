package domain.piece.type.unrestricted;

import java.util.List;

import domain.piece.Camp;

public class Bishop extends UnrestrictedPiece {
    public Bishop(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        if (isNotDiagonal(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
