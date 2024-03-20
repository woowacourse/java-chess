package domain.piece;

import domain.Color;
import domain.Square;
import domain.Vector;
import java.util.List;

public class King extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.KING;

    public King(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final Vector vector = Vector.of(source, target);

        if (vector.maxAxiosSize() == 1) {
            return List.of(target);
        }

        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
