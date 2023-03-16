package domain.piece;

import domain.Square;
import java.util.List;

public class King extends Piece {

    private static final List<DirectionVector> directions = List.of(DirectionVector.values());
    private static final int STEP_SIZE = 1;

    public King(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vectorr vector = dest.calculateVector(src);
        validateDirection(vector);
        validateStepSize(vector);
        return List.of(dest);
    }

    private void validateDirection(Vectorr vector) {
        directions.stream()
            .filter(direction -> direction.isSameDirection(vector))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 방향으로 갈 수 없습니다."));
    }

    private void validateStepSize(Vectorr vector) {
        if (vector.getMaxLength() != STEP_SIZE) {
            throw new IllegalArgumentException("움직일 수 있는 범위를 초과합니다.");
        }
    }
}
