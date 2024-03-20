package domain.piece;

import domain.Color;
import domain.Square;
import domain.Vector;
import java.util.List;

public class Knight extends Piece {

    private static final List<Vector> vectors = List.of(
            new Vector(1, 2), new Vector(1, -2), new Vector(-1, 2), new Vector(-1, -2),
            new Vector(2, 1), new Vector(2, -1), new Vector(-2, 1), new Vector(-2, -1));
    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final Vector targetVector = Vector.of(source, target);

        final boolean canMove = vectors.stream()
                .anyMatch(vector -> vector.equals(targetVector));

        if (canMove) {
            return List.of(target);
        }

        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
