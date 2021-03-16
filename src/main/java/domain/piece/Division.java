package domain.piece;

import domain.position.Position;

import java.util.List;

public abstract class Division implements Piece {
    private final String color;

    public Division(String color) {
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
