package domain.pieceType;

import domain.Color;
import domain.Square;

public class Pawn extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
