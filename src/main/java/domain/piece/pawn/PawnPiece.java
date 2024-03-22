package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;

public abstract class PawnPiece implements Piece {
    private final Color color;

    protected PawnPiece(Color color) {
        this.color = color;
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public final Type type() {
        return Type.PAWN;
    }
}
