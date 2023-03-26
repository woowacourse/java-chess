package domain.piece;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.board.Square;

public abstract class Piece {
    protected static final int FILE_INDEX = 0;
    protected static final int RANK_INDEX = 1;
    protected static final Integer MIN_FILE_INDEX = 0;
    protected static final Integer MAX_FILE_INDEX = 7;
    protected static final Integer MIN_RANK_INDEX = 0;
    protected static final Integer MAX_RANK_INDEX = 7;

    protected final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public abstract List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare);

    public abstract boolean canMove(Map<Square, Piece> squares, Square targetSquare);

    protected boolean isValidRange(int fileIndex, int rankIndex) {
        return fileIndex >= MIN_FILE_INDEX
                && fileIndex <= MAX_FILE_INDEX
                && rankIndex >= MIN_RANK_INDEX
                && rankIndex <= MAX_RANK_INDEX;
    }

    // protected boolean isValidRange(int currentFile, int currentRank, List<Integer> possibleMove) {
    //     return currentFile + possibleMove.get(FILE_INDEX) >= MIN_FILE_INDEX
    //         && currentFile + possibleMove.get(FILE_INDEX) <= MAX_FILE_INDEX
    //         && currentRank + possibleMove.get(RANK_INDEX) >= MIN_RANK_INDEX
    //         && currentRank + possibleMove.get(RANK_INDEX) <= MAX_RANK_INDEX;
    // }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Piece piece = (Piece)o;
        return camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp);
    }
}
