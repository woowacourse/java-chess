package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class Rook extends Piece {
    public Rook(PieceColor color) {
        super(PieceNamePattern.apply(color, "r"), color);
    }
}
