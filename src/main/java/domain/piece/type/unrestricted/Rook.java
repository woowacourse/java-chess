package domain.piece.type.unrestricted;

import java.util.List;

import domain.piece.Camp;
import domain.piece.type.PieceType;

public class Rook extends UnrestrictedPiece {
    public Rook(Camp camp) {
        super(camp, PieceType.ROOK);
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        if (isNotForward(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
