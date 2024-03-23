package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    /*
        firstMove => moveTwo + no enemy
        ordinary => moveOne + no enemy
        opposite => diagonal + have enemy
     */
    @Override
    protected boolean isReachable(final Vector sourceVector, final Piece targetPiece) {
        List<Vector> vectors = new ArrayList<>();
        defaultMovement(targetPiece, vectors);
        attackMovement(targetPiece, vectors);
        vectors = inverseIfBlack(vectors);
        return vectors.stream().anyMatch(vector -> vector.equals(sourceVector));
    }

    protected void defaultMovement(final Piece targetPiece, final List<Vector> vectors) {
        if (targetPiece.isEmpty()) {
            vectors.add(Vector.of(0, 1));
        }
    }

    protected void attackMovement(final Piece targetPiece, final List<Vector> vectors) {
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
