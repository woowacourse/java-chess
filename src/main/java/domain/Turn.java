package domain;

import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.piece.Side;
import domain.position.Position;

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
