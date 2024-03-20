package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

public final class Knight extends Piece {
    public Knight(PieceColor color) {
        super(PieceNamePattern.apply(color, "n"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
