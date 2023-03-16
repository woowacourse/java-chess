package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class ChessGame {
    private final Chessboard chessboard;
    private Camp turn;

    public ChessGame() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
        turn = Camp.WHITE;
    }

    public void move(Square source, Square target) {
        validateTurn(source);
        if (canMove(source, target)) {
            chessboard.swapPiece(source, target);
            turn = turn.getOpposite();
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다");
    }

    private void validateTurn(Square square) {
        Piece pieceAtSquare = chessboard.getPieceAt(square);

        if (pieceAtSquare.isOpposite(turn)) {
            throw new IllegalArgumentException("해당 위치에는 당신의 Piece가 없습니다.");
        }
    }

    private boolean canMove(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);
        Piece targetPiece = chessboard.getPieceAt(target);

        // 1차 검증 - soruce == target 같은가 ? : 다르다 -> 2차 검증
        if (source.equals(target)) {
            return false;
        }

        // 2차 검증 - 해당 piece가 target까지 갈 수 있는가 ? (Piece.canMove()) : true -> 3차 검증
        if (sourcePiece.canMove(source, target)) {
            if (sourcePiece.getPieceType() == PieceType.KNIGHT) {
                // 4차 검증 - target에 아군이 있는가 ? : 없다 -> chessboard.swapPiece
                if (sourcePiece.isNotSameCamp(targetPiece)) {
                    return true;
                }
                return false;
            }
            // 추가 검증 - pawn인 경우
            if (sourcePiece.getPieceType() == PieceType.PAWN) {
                // pawn이면, 1. target에 다른 piece가 존재하면 이동 불가 (경로는 이미 검증)
                //          2.
                if (source.isSameFile(target)) {
                    return chessboard.isEmptyInRoute(source, target) &&
                            chessboard.getPieceAt(target).getPieceType() == PieceType.EMPTY;
                }

                // 대각선 일 때 자신과 다른 색상이여야함
                return sourcePiece.isOpposite(targetPiece);
            }
            // 3차 검증 - 해당 경로에 장애물이 있는가 ? : 없다 -> 4차 검증 (이때, knight는 해당 검증 제외)
            if (chessboard.isEmptyInRoute(source, target)) {
                // 4차 검증 - target에 아군이 있는가 ? : 없다 -> chessboard.swapPiece
                return sourcePiece.isNotSameCamp(targetPiece);
            }
        }
        return false;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
}
