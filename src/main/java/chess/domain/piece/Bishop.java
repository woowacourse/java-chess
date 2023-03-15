package chess.domain.piece;

import static chess.domain.piece.DirectionVector.NORTHEAST;
import static chess.domain.piece.DirectionVector.NORTHWEST;
import static chess.domain.piece.DirectionVector.SOUTHEAST;
import static chess.domain.piece.DirectionVector.SOUTHWEST;

import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final List<DirectionVector> directions = List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final DirectionVector directionVector = findDirectionVector(source, destination);
        return generateRoute(directionVector, source, destination);
    }

    private DirectionVector findDirectionVector(final Square source, final Square destination) {
        final int distanceX = destination.calculateDistanceX(source);
        final int distanceY = destination.calculateDistanceY(source);
        return directions.stream()
                .filter(directions -> directions.isOnMyWay(distanceX, distanceY))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 이동할 수 없습니다."));
    }

    private List<Square> generateRoute(final DirectionVector direction, final Square source, final Square destination) {
        final List<Square> route = new ArrayList<>();
        Square currentSquare = source;
        while (!currentSquare.equals(destination)) {
            currentSquare = currentSquare.next(direction);
            route.add(currentSquare);
        }
        return route;
    }
}
