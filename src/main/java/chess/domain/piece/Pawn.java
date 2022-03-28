package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.CoordinateY;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final double SCORE = 1;
    private static final int INITIAL_WHITE_COORDINATE_Y = 2;
    private static final int INITIAL_BLACK_COORDINATE_Y = 7;
    private static final int DEFAULT_FORWARD = 1;
    private static final int INITIAL_FORWARD = 2;

    public Pawn(final Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (isForwardDiagonal(board, from, to) || isValidForward(board, from, to)) {
            return;
        }
        throw new IllegalArgumentException("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    private boolean isForwardDiagonal(final Board board, final Position from, final Position to) {
        return isValidDirection(from, to) && isDiagonal(from, to) && board.hasPiece(to);
    }

    private boolean isValidDirection(final Position from, final Position to) {
        if (color == Color.WHITE) {
            return from.getCoordinateY() < to.getCoordinateY();
        }
        if (color == Color.BLACK) {
            return from.getCoordinateY() > to.getCoordinateY();
        }
        throw new IllegalStateException("잘못된 색상 정보 입니다.");
    }

    private boolean isValidForward(final Board board, final Position from, final Position to) {
        if (!isValidDirection(from, to) || !isForward(from, to)) {
            return false;
        }
        board.checkHasPiece(to);
        return true;
    }

    private boolean isForward(final Position from, final Position to) {
        if (!isVertical(from, to)) {
            return false;
        }

        int initialCoordinateY = getInitialCoordinateY();
        int start = from.getCoordinateY();
        int yDistance = CoordinateY.difference(from.getCoordinateY(), to.getCoordinateY());

        return isInitForward(start, initialCoordinateY, yDistance) || isDefaultForward(start, initialCoordinateY, yDistance);
    }

    private int getInitialCoordinateY() {
        if (color == Color.WHITE) {
            return INITIAL_WHITE_COORDINATE_Y;
        }
        return INITIAL_BLACK_COORDINATE_Y;
    }

    private boolean isInitForward(final int start, final int initialCoordinateY, final int distance) {
        return start == initialCoordinateY && (distance == DEFAULT_FORWARD || distance == INITIAL_FORWARD);
    }

    private boolean isDefaultForward(final int start, final int initialCoordinateY, final int distance) {
        return start != initialCoordinateY && distance == DEFAULT_FORWARD;
    }
}
