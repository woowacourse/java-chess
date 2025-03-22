package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public void validateMovable(Board board, Position start, Position goal) {

    }
}
