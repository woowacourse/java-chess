package domain.piece;

import domain.board.position.Vector;
import java.util.List;

public abstract class AbstractPawn extends Piece {
    private static final double SCORE_HIGH = 1;

    protected AbstractPawn(final Color color) {
        super(color, SCORE_HIGH);
    }

    protected List<Vector> getWhiteOneStepForwardVectors(final Piece targetPiece) {
        if (targetPiece.isEmpty()) {
            return List.of(Vector.of(0, 1));
        }
        return List.of();
    }

    protected List<Vector> getWhiteOneStepAttackVectors(final Piece targetPiece) {
        if (!targetPiece.isEmpty()) {
            return List.of(Vector.of(1, 1), Vector.of(-1, 1));
        }
        return List.of();
    }

    protected Vector reflectRankIfBlack(final Vector vector) {
        if (getColor() == Color.BLACK) {
            return vector.reflectHorizontally();
        }
        return vector;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
