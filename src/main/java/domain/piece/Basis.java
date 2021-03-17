package domain.piece;

import domain.position.Position;

public abstract class Basis implements Piece{
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract boolean canMove(Position from, Position to);

    @Override
    public String display() {
        return displayName;
    }
}
