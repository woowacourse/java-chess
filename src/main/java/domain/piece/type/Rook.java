package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

public final class Rook extends Piece {
    public Rook(PieceColor color) {
        super(PieceNamePattern.apply(color, "r"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
