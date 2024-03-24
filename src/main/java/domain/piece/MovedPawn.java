package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.ArrayList;
import java.util.List;

public class MovedPawn extends Piece {

    public MovedPawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector sourceVector, final Piece targetPiece) {
        List<Vector> movements = new ArrayList<>();
        addDefaultMovement(targetPiece, movements);
        addAttackMovement(targetPiece, movements);
        movements = inverseIfBlack(movements);
        return movements.stream().anyMatch(vector -> vector.equals(sourceVector));
    }

    protected void addDefaultMovement(final Piece targetPiece, final List<Vector> vectors) {
        if (targetPiece.isEmpty()) {
            vectors.add(Vector.of(0, 1));
        }
    }

    protected void addAttackMovement(final Piece targetPiece, final List<Vector> vectors) {
        if (!targetPiece.isEmpty()) {
            vectors.add(Vector.of(1, 1));
            vectors.add(Vector.of(-1, 1));
        }
    }

    protected List<Vector> inverseIfBlack(List<Vector> vectors) {
        if (color() == Color.BLACK) {
            vectors = vectors.stream().map(Vector::reflectHorizontally).toList();
        }
        return vectors;
    }

}
