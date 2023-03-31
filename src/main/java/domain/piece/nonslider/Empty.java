package domain.piece.nonslider;

import java.util.List;
import java.util.Map;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Empty extends Piece {

    public Empty() {
        super(Camp.EMPTY);
    }

    public static Empty getInstance() {
        return SingleEmptyInstanceHolder.INSTANCE;
    }

    @Override
    public List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare) {
        throw new IllegalArgumentException("기물이 없습니다.");
    }

    @Override
    public boolean canMove(Map<Square, Piece> squares, Square targetSquare) {
        return false;
    }

    private static class SingleEmptyInstanceHolder {
        private static final Empty INSTANCE = new Empty();
    }
}
