package domain.piece;

import domain.position.Position;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract void move(Position to, Pieces pieces);

    public abstract void kill(Position to, Pieces pieces);

    public abstract Position getPosition();

    @Override
    public String display() {
        return displayName;
    }
}
