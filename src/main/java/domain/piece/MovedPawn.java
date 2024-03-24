package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;
import java.util.List;
import java.util.stream.Stream;

public class MovedPawn extends AbstractPawn {

    public MovedPawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isInstanceReachable(final Vector sourceVector, final Piece targetPiece) {
        final List<Vector> possibleVectors = Stream.of(
                getWhiteOneStepForwardVectors(targetPiece),
                getWhiteOneStepAttackVectors(targetPiece)
        ).flatMap(List::stream).toList();

        return possibleVectors.stream().map(this::reflectRankIfBlack).anyMatch(vector -> vector.equals(sourceVector));
    }
}
