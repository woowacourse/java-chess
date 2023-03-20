package chess.domain.movement;

import static chess.domain.movement.Continuity.*;
import static chess.domain.movement.Direction.*;

import java.util.Arrays;

public enum Movement {

    CONTINUOUS_STRAIGHT(CONTINUOUS, STRAIGHT),
    DISCONTINUOUS_STRAIGHT(DISCONTINUOUS, STRAIGHT),
    CONTINUOUS_DIAGONAL(CONTINUOUS, DIAGONAL),
    DISCONTINUOUS_DIAGONAL(DISCONTINUOUS, DIAGONAL),
    CONTINUOUS_L_SHAPE(CONTINUOUS, L_SHAPE);

    private final Continuity continuity;
    private final Direction direction;

    Movement(final Continuity continuity, final Direction direction) {
        this.continuity = continuity;
        this.direction = direction;
    }

    public static Movement of(int fileInterval, int rankInterval) {
        Continuity continuity = Continuity.of(fileInterval, rankInterval);
        Direction direction =  Direction.of(fileInterval, rankInterval);
        return findMovement(continuity, direction);
    }

    private static Movement findMovement(final Continuity continuity, final Direction direction) {
        return Arrays.stream(Movement.values())
                .filter(movement -> movement.isCorrectMovement(continuity, direction))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 움직임입니다."));
    }

    private boolean isCorrectMovement(final Continuity continuity, final Direction direction) {
        return this.continuity == continuity && this.direction == direction;
    }
}
