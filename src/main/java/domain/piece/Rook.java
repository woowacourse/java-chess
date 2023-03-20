package domain.piece;

import domain.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Rook extends Piece {

    private static final List<DirectionVector> directions = List.of(DirectionVector.TOP, DirectionVector.RIGHT, DirectionVector.BOTTOM, DirectionVector.LEFT);

    public Rook(TeamColor teamColor) {
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
        return directions.stream()
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
