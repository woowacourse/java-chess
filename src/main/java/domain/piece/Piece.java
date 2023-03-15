package domain.piece;

import java.util.List;

import domain.board.Square;

public abstract class Piece {
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 7;
    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }

    protected static final int FILE = 0;
    protected static final int RANK = 1;

    public boolean isWhite() {
        return camp.equals(Camp.WHITE);
    }

    abstract public List<Square> fetchMovableCoordinate(Square currentSquare, Square targetSquare);

    protected boolean isInCoordinateRange(int fileCoordinate, int rankCoordinate) {
        return fileCoordinate < MIN_RANGE || fileCoordinate > MAX_RANGE || rankCoordinate < MIN_RANGE || rankCoordinate > MAX_RANGE;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

}
