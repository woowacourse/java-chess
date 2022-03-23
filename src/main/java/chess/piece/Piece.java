package chess.piece;

import static chess.PieceType.*;

import chess.*;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected final PieceType type;
    protected Position position;

    public Piece(Color color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public abstract void move(Position to);

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects
            .equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + type +
            ", position=" + position +
            '}';
    }

    public static Piece knight(Color color, Position position) {
        return new Piece(color, KNIGHT, position) {
            @Override
            public void move(Position to) {
            }
        };
    }
}
