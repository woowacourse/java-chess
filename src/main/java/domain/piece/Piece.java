package domain.piece;

import domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    protected final Color color; // TODO: 접근제어자 열어도 괜찮을까?

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    public abstract String display(); // TODO: view 로직 분리

    public boolean isBlack() {
        return color.isBlack();
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
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
