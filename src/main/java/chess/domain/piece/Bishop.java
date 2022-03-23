package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(new Name("B"), color);
    }


    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }
}
