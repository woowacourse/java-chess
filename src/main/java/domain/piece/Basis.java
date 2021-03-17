package domain.piece;

import domain.position.Position;

import java.util.List;

public abstract class Basis implements Piece{
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract void move(Position to, List<Position> pieces);

    @Override
    public String display() {
        return displayName;
    }
}
