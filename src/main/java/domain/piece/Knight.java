package domain.piece;

import domain.Square;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private static final List<Vectorr> directions = List.of(
        Vectorr.of(1, 2), Vectorr.of(2, 1),
        Vectorr.of(2, -1), Vectorr.of(1, -2),
        Vectorr.of(-1, -2), Vectorr.of(-2, -1),
        Vectorr.of(-2, 1), Vectorr.of(-1, 2));

    public Knight(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vectorr vector = dest.calculateVector(src);
        Optional<Vectorr> direction = findDirection(vector);

        if (direction.isEmpty()) {
            return Collections.emptyList();
        }

        return List.of(dest);
    }

    private Optional<Vectorr> findDirection(Vectorr vector) {
        return directions.stream()
            .filter(direction -> direction.equals(vector))
            .findAny();
    }
}
