package domain;

import domain.piece.Piece;

public class Turn {

    private Side side = Side.WHITE;

    public void check(ChessBoard chessBoard, Position current) {
        Piece currentPiece = chessBoard.findPiece(current);
        if (!currentPiece.isSame(side)) {
            throw new IllegalArgumentException("해당 진영의 턴이 아닙니다.");
        }
    }

    public void end() {
        side = side.opponent();
    }
}
