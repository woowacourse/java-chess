package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceNamePattern;
import domain.position.Position;

public final class Queen extends Piece {
    public Queen(PieceColor color) {
        super(PieceNamePattern.apply(color, "q"), color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
