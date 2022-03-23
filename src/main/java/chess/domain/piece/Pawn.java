package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(new Name("P"), color);
    }

    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }
}
