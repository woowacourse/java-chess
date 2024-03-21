package domain.piece;

import domain.position.Position;
import java.util.Objects;

public class Piece {

    private final Type type;
    private final Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    private boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean canMove(Position source, Position target) {
        if (isBlack() && isSameType(Type.PAWN)) {
            return source.isForwardStraight(target, true);
        }
        return type.canMove(source, target);
    }

    public boolean isDifferentColor(Piece targetPiece) {
        return color != targetPiece.color;
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
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
