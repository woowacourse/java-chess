package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.ArrayList;
import java.util.List;

public class InitPawn extends MovedPawn {
    public InitPawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector sourceVector, final Piece targetPiece) {
        List<Vector> movements = new ArrayList<>();
        addDefaultMovement(targetPiece, movements);
        addAttackMovement(targetPiece, movements);
        addInitMovement(targetPiece, movements);
        movements = inverseIfBlack(movements);
        return movements.stream().anyMatch(vector -> vector.equals(sourceVector));
    }

    private void addInitMovement(final Piece targetPiece, final List<Vector> vectors) {
        if (targetPiece.isEmpty()) {
            vectors.add(Vector.of(0, 2));
        }
    }

    @Override
    public Piece move() {
        return new MovedPawn(color());
    }
}
