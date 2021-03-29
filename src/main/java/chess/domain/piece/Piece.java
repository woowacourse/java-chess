package chess.domain.piece;

import chess.domain.order.MoveRoute;

public abstract class Piece {
    private final String notation;

    public Piece(String notation) {
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public abstract boolean canMove(MoveRoute moveRoute);

    public boolean isBlank() {
        return this.equals(new Blank());
    }

    public boolean isNotBlank() {
        return !this.equals(new Blank());
    }

    public abstract Color getColor();

    public abstract boolean isSameColor(Color color);

    public abstract boolean isSameColor(Piece piece);
}
