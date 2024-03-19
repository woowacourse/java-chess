package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class King extends Piece {
    public King(PieceColor color) {
        super(PieceNamePattern.apply(color, "k"), color);
    }
}
