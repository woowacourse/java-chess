package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class Pawn extends Piece {

    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int MIN_FORWARD = 1;
    private static final int MAX_FORWARD = 2;
    private static final int PAWN_SCORE = 1;

    public Pawn(final Color color) {
        super(color, PieceNotation.PAWN, PAWN_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        if (canMoveDiagonal(board, from, to) || canMoveFront(board, from, to)) {
            return;
        }
        throw new IllegalArgumentException("폰을 해당 위치로 움직일 수 없습니다.");
    }

    private boolean canMoveDiagonal(final Board board, final Position from, final Position to) {
        return isForwardDirection(from, to) && isDiagonal(from, to) && board.hasPiece(to);
    }

    private boolean isForwardDirection(final Position from, final Position to) {
        if (color == Color.WHITE) {
            return from.getRankNumber() < to.getRankNumber();
        }
        if (color == Color.BLACK) {
            return from.getRankNumber() > to.getRankNumber();
        }
        throw new IllegalStateException("잘못된 색상 정보 입니다.");
    }

    private boolean isDiagonal(final Position from, final Position to) {
        final var fileDifference = Math.abs(from.getFileOrder() - to.getFileOrder());
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        return fileDifference == MIN_FORWARD && rankDifference == MIN_FORWARD;
    }

    private boolean canMoveFront(final Board board, final Position from, final Position to) {
        return canMoveFront(from, to) && !board.hasPiece(to);
    }

    private boolean canMoveFront(final Position from, final Position to) {
        if (isVerticalMove(from, to) && isForwardDirection(from, to)) {
            return isFrontMove(from, to);
        }
        return false;
    }

    private boolean isFrontMove(final Position from, final Position to) {
        final var initPawnRank = getPawnInitRank();
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        if (rankDifference == MAX_FORWARD) {
            return from.getRankNumber() == initPawnRank;
        }
        return rankDifference == MIN_FORWARD;
    }

    private boolean isVerticalMove(final Position from, final Position to) {
        return Math.abs(from.getFileOrder() - to.getFileOrder()) == 0;
    }

    private int getPawnInitRank() {
        if (color == Color.WHITE) {
            return WHITE_INIT_RANK;
        }
        return BLACK_INIT_RANK;
    }
}
