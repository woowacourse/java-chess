package domain.piece.type;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Empty extends Piece {
    private static class singleEmptyInstanceHolder {
        public static final Empty INSTANCE = new Empty();
    }

    private Empty() {
        super(Camp.NONE);
    }

    public static Empty getInstance() {
        return singleEmptyInstanceHolder.INSTANCE;
    }


    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        throw new IllegalArgumentException("기물이 없습니다.");
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {

    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
