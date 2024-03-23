package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Vector;
import java.util.ArrayList;
import java.util.List;

public class InitPawn extends Pawn {
    public InitPawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector sourceVector, final Piece targetPiece) {
        List<Vector> vectors = new ArrayList<>();
        addDefaultMovement(targetPiece, vectors);
        addAttackMovement(targetPiece, vectors);
        addInitMovement(targetPiece, vectors);
        vectors = inverseIfBlack(vectors);
        return vectors.stream().anyMatch(vector -> vector.equals(sourceVector));
    }

    private void addInitMovement(final Piece targetPiece, final List<Vector> vectors) {
        if (targetPiece.isEmpty()) {
            vectors.add(Vector.of(0, 2));
        }
    }

    @Override
    public boolean isInitPawn() {
        return true;
    }
}
