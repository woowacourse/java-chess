package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public void validateMovable(Board board, Position start, Position goal) {

    }
}
