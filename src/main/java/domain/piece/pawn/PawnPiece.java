package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;

public abstract class PawnPiece implements Piece {
    private final Color color;

    protected PawnPiece(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
