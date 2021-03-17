package domain.piece;

import domain.position.Position;

import java.util.List;

public abstract class Basis implements Piece{
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract void move(Position to, List<Piece> pieces);

    public abstract void kill(Position to, List<Piece> pieces);

    public abstract Position getPosition();

    @Override
    public String display() {
        return displayName;
    }
}
