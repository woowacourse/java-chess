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
    protected boolean isReachable(final Vector sourceVector, final Piece targetPiece) {
        List<Vector> vectors = new ArrayList<>();
        defaultMovement(vectors);
        firstMovement(vectors);
        attackMovement(targetPiece, vectors);
        vectors = inverseIfBlack(vectors);
        return vectors.stream().anyMatch(vector -> vector.equals(sourceVector));

    }

    private void firstMovement(final List<Vector> vectors) {
        vectors.add(Vector.of(0, 2));
    }
}
