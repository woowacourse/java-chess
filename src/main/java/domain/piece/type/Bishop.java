package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

public final class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(PieceNamePattern.apply(color, "b"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
