package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

public final class King extends Piece {
    public King(PieceColor color) {
        super(PieceNamePattern.apply(color, "k"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
