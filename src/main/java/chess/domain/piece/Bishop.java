package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
