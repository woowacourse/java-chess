package domain.piece;

import domain.Color;
import domain.Rank;
import domain.Square;
import domain.Vector;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Vector> blackVectors = List.of(new Vector(0, -1), new Vector(1, -1), new Vector(-1, -1));
    private static final List<Vector> whiteVectors = List.of(new Vector(0, 1), new Vector(-1, 1), new Vector(1, 1));
    private static final Vector blackStartVector = new Vector(0, -2);
    private static final Vector whiteStartVector = new Vector(0, 2);

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        if (canMove(source, target)) {
            return List.of(target);
        }
        return List.of();
    }

    private boolean canMove(final Square source, final Square target) {
        final Vector targetVector = Vector.of(source, target);

        Rank rank = Rank.SEVEN;
        List<Vector> vectors = blackVectors;
        Vector startVector = blackStartVector;

        if (color == Color.WHITE) {
            rank = Rank.TWO;
            vectors = whiteVectors;
            startVector = whiteStartVector;
        }

        return (source.getRank() == rank && startVector.equals(targetVector))
                || vectors.stream().anyMatch(vector -> vector.equals(targetVector));
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
