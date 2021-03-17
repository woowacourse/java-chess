package domain.piece;

import domain.position.Position;

import java.util.Locale;

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

    @Override
    public String display() {
        if(isBlack()) {
            return super.display().toUpperCase();
        }
        return super.display();
    }
}
