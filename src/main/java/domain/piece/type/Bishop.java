package domain.piece.type;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Bishop extends Piece {
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
