package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;

public abstract class Piece {
    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public abstract void validateMovable(Board board, Position start, Position goal);
}
