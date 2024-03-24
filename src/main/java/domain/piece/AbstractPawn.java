package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractPawn extends Piece {


    protected AbstractPawn(final Color color) {
        super(color);
    }

    protected List<Vector> addDefaultMovement(final Piece targetPiece) {
        if (targetPiece.isEmpty()) {
            return List.of(Vector.of(0, 1));
        }
        return List.of();
    }

    protected List<Vector> addAttackMovement(final Piece targetPiece) {
        if (!targetPiece.isEmpty()) {
            return List.of(Vector.of(1, 1), Vector.of(-1, 1));
        }
        return List.of();
    }

    protected Vector inverseIfBlack(final Vector vector) {
        if (color() == Color.BLACK) {
            return vector.reflectHorizontally();
        }
        return vector;
    }
}
