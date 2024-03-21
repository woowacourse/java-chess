package domain.piece;

import domain.Camp;
import domain.Square;
import domain.SquareVector;

public class Queen extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.QUEEN;

    public Queen(final Camp color) {
        super(color);
    }


    @Override
    public boolean canMove(final Square source, final Square target) {
        final SquareVector squareVector = SquareVector.of(source, target);
        return squareVector.isDiagonal() || squareVector.isHorizontalOrVertical();
    }


    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
