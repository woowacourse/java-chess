package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.square.Square;
import chess.domain.piece.Piece;

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
