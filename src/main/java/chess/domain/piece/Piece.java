package chess.domain.piece;

import chess.domain.board.Path;

public abstract class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    abstract boolean canMove(Path path);

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }
}
