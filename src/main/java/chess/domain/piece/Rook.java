package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class Rook extends Piece {
    public Rook(Color color) {
        super(new Name("R"), color);
    }

    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }
}
