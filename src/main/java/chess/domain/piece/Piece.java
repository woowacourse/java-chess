package chess.domain.piece;

import java.util.Objects;

import chess.domain.board.Position;

public abstract class Piece {

    private final String name;
    private final Color color;

    protected Piece(final String name, final Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract Direction findValidDirection(Position current, Position target);

    public boolean isSamePiece(final String expected) {
        return this.getName().equals(expected);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece)o;
        return Objects.equals(name, piece.name) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }
}
