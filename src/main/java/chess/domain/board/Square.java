package chess.domain.board;

import chess.domain.board.piece.Empty;
import chess.domain.board.piece.Piece;

public class Square {
    private final Vertical vertical;
    private final Horizontal horizontal;
    private Piece piece;

    public Square(Vertical vertical, Horizontal horizontal, Piece piece) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.piece = piece;
    }

    public Square(Vertical vertical, Horizontal horizontal) {
        this(vertical, horizontal, Empty.getInstance());
    }

    @Override
    public String toString() {
        return piece.makeSymbol();
    }
}
