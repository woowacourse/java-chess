package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.piece.Side;
import domain.square.Square;

public class Turn {

    private Side side = Side.WHITE;

    public void check(Board board, Square current) {
        Piece currentPiece = board.findPiece(current);
        if (!currentPiece.isSame(side)) {
            throw new IllegalArgumentException("해당 진영의 턴이 아닙니다.");
        }
    }

    public void end() {
        side = side.opponent();
    }
}
