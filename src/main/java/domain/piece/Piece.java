package domain.piece;

import domain.position.Position;

import java.util.Objects;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean canMove(Position source, Position target) {
        if (isSameType(PieceType.PAWN)) {
            return color.canMove(source, target) && pieceType.canMove(source, target);
        }
        return pieceType.canMove(source, target);
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
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
