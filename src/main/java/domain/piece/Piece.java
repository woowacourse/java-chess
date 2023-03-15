package domain.piece;

import java.util.List;
import java.util.Map;

import domain.board.Square;

public abstract class Piece {
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 7;
    protected static final int FILE = 0;
    protected static final int RANK = 1;

    public Camp getCamp() {
        return camp;
    }

    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }

    public boolean isWhite() {
        return camp.equals(Camp.WHITE);
    }


    abstract public List<Square> fetchMovePath(Square currentSquare, Square targetSquare);

    public boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare) {
        Camp targetCamp = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        return isDifferentCampOrEmptyOnTarget(targetCamp) && !isExistPieceOnPath(pathInfo);
    }

    private boolean isDifferentCampOrEmptyOnTarget(Camp targetCamp) {
        return isDifferentCamp(targetCamp) || targetCamp.equals(Camp.NONE);
    }

    protected boolean isExistPieceOnPath(Map<Square, Camp> pathInfo) {
        return pathInfo.values().stream()
                .anyMatch(camp -> camp != Camp.NONE);
    }

    protected boolean isInCoordinateRange(int fileCoordinate, int rankCoordinate) {
        return fileCoordinate < MIN_RANGE || fileCoordinate > MAX_RANGE || rankCoordinate < MIN_RANGE
                || rankCoordinate > MAX_RANGE;
    }

    protected boolean isDifferentCamp(Camp otherCamp) {
        Camp camp = this.camp;
        if (camp.equals(Camp.BLACK) && otherCamp.equals(Camp.WHITE)) {
            return true;
        }
        if (camp.equals(Camp.WHITE) && otherCamp.equals(Camp.BLACK)) {
            return true;
        }
        return false;
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

    public boolean isEmpty() {
        return false;
    }
}
