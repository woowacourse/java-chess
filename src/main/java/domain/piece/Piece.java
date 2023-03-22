package domain.piece;

import java.util.List;
import java.util.Map;

import domain.board.Square;

public abstract class Piece {
    protected static final int FILE_INDEX = 0;
    protected static final int RANK_INDEX = 1;

    protected final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public abstract List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare);

    public abstract boolean canMove(Map<Square, Piece> squares, Square targetSquare);

    protected boolean isOppositeCamp(Piece piece) {
        return (isWhite() == piece.isBlack() || isBlack() == piece.isWhite()) && !piece.isEmpty();
    }

    public boolean isWhite() {
        return camp == Camp.WHITE;
    }

    public boolean isBlack() {
        return camp == Camp.BLACK;
    }

    public boolean isEmpty() {
        return camp == Camp.EMPTY;
    }
}
