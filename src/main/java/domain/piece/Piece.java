package domain.piece;

import domain.board.Turn;
import domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    public boolean canAttack(Position source, Position target) {
        return canMove(source, target);
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isNotBlank() {
        return true;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    private boolean isWhite() {
        return color.isWhite();
    }

    public boolean isOppositeSide(Piece targetPiece) {
        return this.color.isOpposite(targetPiece.color);
    }

    public boolean isSameColor(Piece targetPiece) {
        return (isBlack() && targetPiece.isBlack()) || (isWhite() && targetPiece.isWhite());
    }

    public boolean isNotTurn(Turn turn) {
        return turn.isNotTurn(color);
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
