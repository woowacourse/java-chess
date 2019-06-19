package chess.domain;

import java.util.Objects;

public class Square {
    private final Position position;
    private Piece piece;

    private Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public static Square of(final Position position, final Piece piece) {
        return new Square(position, piece);
    }

    public boolean isMovable(final Square target) {
        return piece.isValidMove(this.position, target.position);
    }
    // TODO target이 빈 칸인 경우

    public boolean movePiece(final Square target) {
        if (piece.isValidMove(this.position, target.position)) {
            target.piece = this.piece;
            this.piece = Piece.empty();
            return true;
        }
        return false;
    }

    public boolean isSameTeam(final Square target) {
        return this.piece.isSameTeam(target.piece);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        boolean b = Objects.equals(position, square.position);
        final boolean c = Objects.equals(piece, square.piece);
        return b && c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece);
    }

    @Override
    public String toString() {
        return "Square{" +
                "position=" + position +
                ", piece=" + piece +
                '}';
    }

    public boolean isEmpty() {
        return piece.isEmpty();
    }
}
