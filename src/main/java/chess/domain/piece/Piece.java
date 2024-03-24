package chess.domain.piece;

import chess.domain.board.Route;

public abstract class Piece {
    private final Color color;
    private final String name;

    protected Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public abstract boolean canMove(Route route);

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isAllyPiece(Piece other) {
        return this.color == other.color;
    }

    public String getName() {
        return name;
    }
}
