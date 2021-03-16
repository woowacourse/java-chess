package chess.domain.piece;

import java.util.Objects;

public class Piece {

    private PieceKind pieceKind;
    private PieceColor pieceColor;

    public Piece(PieceKind pieceKind, PieceColor pieceColor) {
        this.pieceKind = pieceKind;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.getPieceColor();
    }

    public String getSymbol() {
        return pieceKind.getName(pieceColor);
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceKind == piece.pieceKind && pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceKind, pieceColor);
    }
}
