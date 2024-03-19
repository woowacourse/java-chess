package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class Knight extends Piece {
    public Knight(PieceColor color) {
        super(PieceNamePattern.apply(color, "n"), color);
    }
}
