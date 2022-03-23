package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class Knight extends Piece {
    public Knight(Color color) {
        super(new Name("N"), color);
    }

    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }
}
