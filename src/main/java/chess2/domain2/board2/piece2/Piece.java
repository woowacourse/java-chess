package chess2.domain2.board2.piece2;

import chess2.domain2.board2.Position;
import java.util.Objects;

public class Piece {

    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean canMove(Position from, Position to) {
        return type.isMovable(from, to);
    }

    public boolean canAttack(Position from, Position to, Piece targetPiece) {
        if(targetPiece.hasColorOf(color)) {
            throw new IllegalArgumentException("아군은 공격할 수 없습니다.");
        }
        return type.isMovable(from, to); // TODO: divide move and attack case
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public boolean hasTypeOf(PieceType type) {
        return this.type == type;
    }

    PieceType type() {
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
