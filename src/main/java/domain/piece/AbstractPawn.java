package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.List;

public abstract class AbstractPawn extends Piece {


    protected AbstractPawn(final Color color) {
        super(color);
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
        if (color() == Color.BLACK) {
            return vector.reflectHorizontally();
        }
        return vector;
    }
}
