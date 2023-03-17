package domain.piece.type;

import java.util.List;

import domain.piece.Camp;
import domain.piece.Piece;

public class Queen extends Piece {
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isQueen() {
        return true;
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        if (isNotForward(gaps) && isNotDiagonal(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }
}
