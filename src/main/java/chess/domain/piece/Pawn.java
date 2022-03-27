package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final double SCORE = 1;
    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int DEFAULT_FORWARD = 1;
    private static final int INIT_FORWARD = 2;

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
            return from.getRank() < to.getRank();
        }
        if (color == Color.BLACK) {
            return from.getRank() > to.getRank();
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

        int initRank = getInitRank();
        int start = from.getRank();
        int rankDistance = Rank.difference(from.getRank(), to.getRank());

        return isInitForward(start, initRank, rankDistance) || isDefaultForward(start, initRank, rankDistance);
    }

    private int getInitRank() {
        if (color == Color.WHITE) {
            return WHITE_INIT_RANK;
        }
        return BLACK_INIT_RANK;
    }

    private boolean isInitForward(final int start, final int initRank, final int distance) {
        return start == initRank && (distance == DEFAULT_FORWARD || distance == INIT_FORWARD);
    }

    private boolean isDefaultForward(final int start, final int initRank, final int distance) {
        return start != initRank && distance == DEFAULT_FORWARD;
    }
}
