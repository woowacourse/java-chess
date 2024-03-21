package domain.piece;

import domain.Camp;
import domain.Square;
import domain.SquareVector;

public class King extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.KING;

    public King(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final SquareVector squareVector = SquareVector.of(source, target);
        final SquareVector direction = squareVector.scaleDown();

        return direction.isManhattanDistance(1);
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
