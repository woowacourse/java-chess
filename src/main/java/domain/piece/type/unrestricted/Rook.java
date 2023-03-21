package domain.piece.type.unrestricted;

import java.util.List;

import domain.piece.Camp;
import domain.piece.type.Type;

public class Rook extends UnrestrictedPiece {
    public Rook(Camp camp, Type type) {
        super(camp, type);
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        if (isNotForward(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
