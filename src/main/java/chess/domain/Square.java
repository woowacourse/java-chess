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

    public boolean isValidMove(final Square target) {
        return piece.isValidMove(this.position, target.position);
    }

    public boolean isValidAttack(final Square target) {
        if (isSameTeam(target)) {
            return false;
        }
        return this.piece.isValidAttack(this.position, target.position);
    }

    private boolean isSameTeam(final Square target) {
        return this.piece.isSameTeam(target.piece);
    }

    public boolean movePiece(final Square target) {
        if (piece.isValidMove(this.position, target.position)) {
            target.piece = piece.orElseFirstPawn();
            this.piece = Piece.empty();
            return true;
        }
        return false;
    }

    public void attackPiece(final Square target) {
        if(target.piece.isKing()){
            throw new ExitException("attacked King");
        }
        target.piece = piece.orElseFirstPawn();
        this.piece = Piece.empty();
    }

    public boolean isEmpty() {
        return piece.isEmpty();
    }

    public boolean isSameColor(final Piece.Color other) {
        return this.piece.isSameColor(other);
    }

    public double getScore() {
        return piece.getScore();
    }

    public boolean isPawn() {
        return piece.isPawn();
    }

    public boolean isSameColumn(final Column other) {
        return this.position.isSameColumn(other);
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
}
