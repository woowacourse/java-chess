package chess2.domain2.board2.piece2;

import chess2.domain2.board2.position.Position;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    abstract public boolean canMove(Position from, Position to);

    public final boolean canAttack(Position from, Position to, Piece targetPiece) {
        if (targetPiece.hasColorOf(color)) {
            throw new IllegalArgumentException("공격할 수 없는 대상입니다.");
        }
        return isAttackableRoute(from, to);
    }

    abstract protected boolean isAttackableRoute(Position from, Position to);

    public final boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public final boolean hasTypeOf(PieceType type) {
        return this.type == type;
    }

    public final PieceType type() {
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
        return color == piece.color
                && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return "Piece{" + color + " " + type + '}';
    }
}
