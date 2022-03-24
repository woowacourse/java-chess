package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class King extends Piece {
    public King(Color color) {
        super(new Name("K"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }
}
