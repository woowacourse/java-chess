package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Knight extends Piece {
    public Knight(Color color) {
        super(new Name("N"), color);
    }


    @Override
    public void canMove(Board board, Position from, Position to) {

    }
}
