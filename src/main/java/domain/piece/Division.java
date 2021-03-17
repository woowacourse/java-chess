package domain.piece;

import domain.position.Position;

public abstract class Division extends Basis {
    private final Color color;

    public Division(Color color, String displayName) {
        super(displayName);
        this.color = color;
    }

    public boolean isBlack() {
        return Color.BLACK.equals(color);
    }

    public boolean isWhite() {
        return Color.WHITE.equals(color);
    }


    public abstract boolean canMove(Position from, Position to);
}
