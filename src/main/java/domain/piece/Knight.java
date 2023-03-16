package domain.piece;

import domain.Square;
import java.util.List;

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
        validateDirection(vector);

        return List.of(dest);
    }

    private void validateDirection(Vectorr vector) {
        directions.stream()
            .filter(direction -> direction.equals(vector))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 방향으로 갈 수 없습니다."));
    }
}
