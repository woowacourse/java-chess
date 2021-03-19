package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract void moveToEmpty(Position to, Pieces pieces);

    public abstract void moveForKill(Position to, Pieces pieces);

    public abstract Position getPosition();

    public abstract boolean isSameColor(Color color);

    public abstract boolean isEmpty();


    public abstract boolean isKing();


    public abstract double score();

    @Override
    public String display() {
        return displayName;
    }
}
