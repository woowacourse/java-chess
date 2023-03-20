package domain.piece;

import domain.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final List<DirectionVector> DIRECTIONS = List.of(
            DirectionVector.TOP_RIGHT, DirectionVector.BOTTOM_RIGHT,
            DirectionVector.BOTTOM_LEFT, DirectionVector.TOP_LEFT
    );

    public Bishop(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vector vector = dest.calculateVector(src);
        Optional<DirectionVector> direction = findDirection(vector);

        if (direction.isEmpty()) {
            return Collections.emptyList();
        }
        return getSquaresToDestination(src, vector, direction.get());
    }

    private Optional<DirectionVector> findDirection(Vector vector) {
        return DIRECTIONS.stream()
                .filter(direction -> direction.isSameDirection(vector))
                .findAny();
    }

    private List<Square> getSquaresToDestination(Square src, Vector vector,
                                                 DirectionVector direction) {
        int maxStep = vector.getMaxLength();
        List<Square> result = new ArrayList<>();
        for (int step = 1; step <= maxStep; step++) {
            Square next = src.add(direction.multiply(step));
            result.add(next);
        }
        return Collections.unmodifiableList(result);
    }
}
