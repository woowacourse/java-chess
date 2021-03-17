package domain.piece;

import domain.position.Position;

public abstract class Division extends Basis {
    private final String color;

    public Division(String color, String displayName) {
        super(displayName);
        this.color = color;
    }

    public boolean isBlack() {
        return "black".equals(color);
    }

    public boolean isWhite() {
        return "white".equals(color);
    }


    public abstract boolean canMove(Position from, Position to);
}
