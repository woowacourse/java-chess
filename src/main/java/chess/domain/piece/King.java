package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class King extends Piece {
    public King(Color color) {
        super(new Name("K"), color);
    }

    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }
}
