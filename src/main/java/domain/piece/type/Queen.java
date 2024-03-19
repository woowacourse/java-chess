package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;

public final class Queen extends Piece {
    public Queen(PieceColor color) {
        super(PieceNamePattern.apply(color, "q"), color);
    }
}
