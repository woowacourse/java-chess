package domain.path.pathValidtor.validateTarget;

import domain.path.Path;
import domain.path.direction.Direction;
import java.util.List;

public final class DirectionValidation implements ValidateTarget {

    private final List<Direction> possibleDirections;

    private DirectionValidation(List<Direction> possibleDirections) {
        this.possibleDirections = possibleDirections;
    }

    public static DirectionValidation of(List<Direction> possibleDirections) {
        return new DirectionValidation(possibleDirections);
    }

    @Override
    public void validate(final Path path) {
        if (possibleDirections.contains(path.getDirection())) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
    }
}
