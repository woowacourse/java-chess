package domain.pieceType;

import domain.Color;
import domain.Square;

public class Knight extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
