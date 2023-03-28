package domain.piece.sliding;

import domain.Square;
import domain.piece.Direction;
import domain.piece.Piece;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(TeamColor teamColor, PieceInfo pieceInfo) {
        super(teamColor, pieceInfo);
    }

    @Override
    public List<Square> findRoutes(Square source, Square destination) {
        Direction vector = destination.calculateVector(source);
        Direction directionVector = findDirection(vector);

        return getSquaresToDestination(source, vector, directionVector);
    }

    protected abstract Direction findDirection(Direction direction);

    private List<Square> getSquaresToDestination(Square source, Direction vector,
        Direction directionVector) {
        int maxStep = vector.getMaxLength();
        List<Square> result = new ArrayList<>();
        for (int step = 1; step <= maxStep; step++) {
            Square next = source.add(directionVector.multiply(step));
            result.add(next);
        }
        return Collections.unmodifiableList(result);
    }
}
