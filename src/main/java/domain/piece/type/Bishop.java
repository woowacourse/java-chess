package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(PieceNamePattern.apply(color, "b"), color);
    }
}
