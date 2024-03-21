package domain.piece;

import domain.Camp;
import domain.Square;
import domain.SquareVector;
import java.util.List;

public class Knight extends Piece {

    private static final List<SquareVector> SQUARE_VECTORS = List.of(
            new SquareVector(1, 2), new SquareVector(1, -2), new SquareVector(-1, 2), new SquareVector(-1, -2),
            new SquareVector(2, 1), new SquareVector(2, -1), new SquareVector(-2, 1), new SquareVector(-2, -1));
    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;

    public Knight(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final SquareVector targetSquareVector = SquareVector.of(source, target);

        return SQUARE_VECTORS.stream()
                .anyMatch(vector -> vector.equals(targetSquareVector));
    }


    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
