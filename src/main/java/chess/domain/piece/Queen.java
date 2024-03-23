package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class Queen extends Piece {
    public Queen(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
