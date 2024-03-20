package domain.piece;

import domain.Color;
import domain.Square;
import domain.Vector;
import java.util.List;
import java.util.stream.Stream;

public class Bishop extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.BISHOP;

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final Vector vector = Vector.of(source, target);

        if (vector.isDiagonal()) {
            final Vector direction = vector.normalizedVector();
            final int size = vector.maxAxiosSize();

            return Stream.iterate(source.next(direction.y(), direction.x()), i -> i.next(direction.y(), direction.x()))
                    .limit(size)
                    .toList();
        }

        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
