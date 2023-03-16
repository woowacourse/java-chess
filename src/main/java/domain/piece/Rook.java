package domain.piece;

import domain.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    private static final List<DirectionVector> directions = List.of(DirectionVector.TOP,
        DirectionVector.RIGHT, DirectionVector.BOTTOM, DirectionVector.LEFT);

    public Rook(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vectorr vector = dest.calculateVector(src);
        DirectionVector direction = findDirection(vector);

        return getSquaresToDestination(src, vector, direction);
    }

    private DirectionVector findDirection(Vectorr vector) {
        return directions.stream()
            .filter(direction -> direction.isSameDirection(vector))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 방향으로 갈 수 없습니다."));
    }

    private List<Square> getSquaresToDestination(Square src, Vectorr vector,
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
