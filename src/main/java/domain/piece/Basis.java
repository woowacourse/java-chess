package domain.piece;

import domain.piece.Piece;

import java.util.List;

public abstract class Basis implements Piece {
    private final String color;

    public Basis(String color) {
        this.color = color;
    }

    public boolean isBlack() {
        return "black".equals(color);
    }

    public boolean isWhite() {
        return "white".equals(color);
    }

    public abstract boolean canMove(List<String> from, List<String> to);
}
