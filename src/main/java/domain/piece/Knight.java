package domain.piece;

import domain.Square;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {

    private static final List<Vector> directions = List.of(
        Vector.of(1, 2), Vector.of(2, 1),
        Vector.of(2, -1), Vector.of(1, -2),
        Vector.of(-1, -2), Vector.of(-2, -1),
        Vector.of(-2, 1), Vector.of(-1, 2));

    public Knight(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vector vector = dest.calculateVector(src);
        Optional<Vector> direction = findDirection(vector);

        if (direction.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of(dest);
    }

    private Optional<Vector> findDirection(Vector vector) {
        return directions.stream()
            .filter(direction -> direction.equals(vector))
            .findAny();
    }
}
