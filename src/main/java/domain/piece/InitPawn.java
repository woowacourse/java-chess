package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InitPawn extends MovedPawn {
    public InitPawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector sourceVector, final Piece targetPiece) {
        final List<Vector> possibleVectors = Stream.of(
                        addDefaultMovement(targetPiece), addAttackMovement(targetPiece), addInitMovement(targetPiece))
                .flatMap(List::stream)
                .toList();

        return possibleVectors.stream()
                .map(this::inverseIfBlack)
                .anyMatch(vector -> vector.equals(sourceVector));
    }

    private List<Vector> addInitMovement(final Piece targetPiece) {
        if (targetPiece.isEmpty()) {
            return List.of(Vector.of(0, 2));
        }
        return List.of();
    }

    @Override
    public Piece move() {
        return new MovedPawn(color());
    }
}
