package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Queen extends Piece {
    public Queen(Color color) {
        super(new Name("Q"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }
}
