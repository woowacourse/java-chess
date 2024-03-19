package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(PieceNamePattern.apply(color, "p"), color);
    }
}
