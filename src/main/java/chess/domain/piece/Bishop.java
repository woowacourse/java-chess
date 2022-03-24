package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(new Name("B"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }
}
