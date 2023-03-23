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
    public List<Square> findRoutes(Square src, Square dest) {
        Direction vector = dest.calculateVector(src);
        Direction direction = findDirection(vector);

        return getSquaresToDestination(src, vector, direction);
    }

    protected abstract Direction findDirection(Direction direction);

    private List<Square> getSquaresToDestination(Square src, Direction vector,
        Direction direction) {
        int maxStep = vector.getMaxLength();
        List<Square> result = new ArrayList<>();
        for (int step = 1; step <= maxStep; step++) {
            Square next = src.add(direction.multiply(step));
            result.add(next);
        }
        return Collections.unmodifiableList(result);
    }
}
