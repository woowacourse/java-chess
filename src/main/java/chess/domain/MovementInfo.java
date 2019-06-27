package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

public class MovementInfo {
    private final Direction direction;
    private final int maxDistance;

    public MovementInfo(final Direction direction, final int maxDistance) {
        this.direction = direction;
        this.maxDistance = maxDistance;
    }

    public static List<MovementInfo> makeMovementInfos(List<Direction> directions, int maxDistance) {
        return directions.stream()
                .map(direction -> new MovementInfo(direction, maxDistance))
                .collect(Collectors.toList());
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMaxDistance() {
        return maxDistance;
    }
}
