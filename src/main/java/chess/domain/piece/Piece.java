package chess.domain.piece;

import chess.domain.order.MoveRoute;

public abstract class Piece {
    private final String name;

    public Piece(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
