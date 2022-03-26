package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int DEFAULT_FORWARD = 1;
    private static final int INIT_FORWARD = 2;

    public Pawn(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (!canMoveDiagonal(board, from, to) && !canMoveForward(board, from, to)) {
            throw new IllegalArgumentException("폰은 앞으로 한 칸만 이동할 수 있습니다.");
        }
    }

    private boolean canMoveDiagonal(final Board board, final Position from, final Position to) {
        return isValidDirection(from, to) && isDiagonal(from, to) && board.hasPiece(to);
    }

    private boolean isDiagonal(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) == 1 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1;
    }

    private boolean isValidDirection(final Position from, final Position to) {
        if (color == Color.WHITE) {
            return from.getRankNumber() < to.getRankNumber();
        }
        if (color == Color.BLACK) {
            return from.getRankNumber() > to.getRankNumber();
        }
        throw new IllegalStateException("잘못된 색상 정보 입니다.");
    }

    private boolean canMoveForward(final Board board, final Position from, final Position to) {
        if (!isValidDirection(from, to) || !isForward(from, to)) {
            return false;
        }
        checkHasPiece(board.getPiece(to));
        return true;
    }

    private boolean isForward(final Position from, final Position to) {
        if (isMovingHorizontal(from, to)) {
            return false;
        }

        int initRank = getInitRank();
        int start = from.getRankNumber();
        int rankDistance = Rank.difference(from.getRankNumber(), to.getRankNumber());

        return isInitForward(start, initRank, rankDistance) || isDefaultForward(start, initRank, rankDistance);
    }

    private int getInitRank() {
        if (color == Color.WHITE) {
            return WHITE_INIT_RANK;
        }
        return BLACK_INIT_RANK;
    }

    private boolean isMovingHorizontal(final Position from, final Position to) {
        return File.difference(from.getFile(), to.getFile()) != 0;
    }

    private boolean isInitForward(final int start, final int initRank, final int distance) {
        return start == initRank && (distance == DEFAULT_FORWARD || distance == INIT_FORWARD);
    }

    private boolean isDefaultForward(final int start, final int initRank, final int distance) {
        return start != initRank && distance == DEFAULT_FORWARD;
    }

    private void checkHasPiece(final Piece target) {
        if (target != null) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }
}

//    private boolean canMoveForward2(final Board board, final Position from, final Position to) {
//        int start = Math.min(from.getRankNumber(), to.getRankNumber());
//        int distance = from.getRankNumber() - to.getRankNumber();
//
//        int initRank = getInitRank();
//
//        //전진 한 건지 확인
//        if (from.getFile() == to.getFile()) {
//            if (color == color.WHITE) {
//                if (from.getRankNumber() < to.getRankNumber()) {
//                    //흰색이 전진함
//                    if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1) {
//                        return board.getPiece(to) == null;
//                    }
//                    if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == 2) {
//                        return from.getRankNumber() == 2 && board.getPiece(to) == null;
//                    }
//                }
//            }
//            if (color == color.BLACK) {
//                if (from.getRankNumber() < to.getRankNumber()) {
//                    //검정색이 전진함
//                    if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1) {
//                        return board.getPiece(to) == null;
//                    }
//                    if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == 2) {
//                        return from.getRankNumber() == 2 && board.getPiece(to) == null;
//                    }
//                }
//            }
//        }
//        return false;
//    }
