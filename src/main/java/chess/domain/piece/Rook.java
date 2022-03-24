package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Rook extends Piece {
    public Rook(Color color) {
        super(new Name("R"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }
}
