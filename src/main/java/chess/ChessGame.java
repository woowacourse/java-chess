package chess;

import chess.piece.Piece;
import chess.piece.PieceType;

public class ChessGame {
    private final Chessboard chessboard;

    public ChessGame() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
    }

    public void move(Square source, Square target) {
        if (canMove(source, target)) {
            chessboard.swapPiece(source, target);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다");
    }

    private boolean canMove(Square source, Square target) {
        Piece sourcePiece = chessboard.getPieceAt(source);
        Piece targetPiece = chessboard.getPieceAt(target);
        // 1차 검증 - soruce == target 같은가 ? : 다르다 -> 2차 검증
        // 2차 검증 - 해당 piece가 target까지 갈 수 있는가 ? (Piece.canMove()) : true -> 3차 검증
        // 3차 검증 - 해당 경로에 장애물이 있는가 ? : 없다 -> 4차 검증 (이때, knight는 해당 검증 제외)
        // 4차 검증 - target에 아군이 있는가 ? : 없다 -> chessboard.swapPiece
        //todo: 폰 대각선 적있을시 이동 가능 및 전방에 적 있을 시 이동 불가능 로직 구현
        if (sourcePiece.canMove(source, target)) {
            if (sourcePiece.getPieceType() == PieceType.KNIGHT) {
                if (sourcePiece.isNotSameCamp(targetPiece)) {
                    return true;
                }
                return false;
            }
            if (chessboard.isEmptyInRoute(source, target)) {
                return sourcePiece.isNotSameCamp(targetPiece);
            }
        }
        return false;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
}
