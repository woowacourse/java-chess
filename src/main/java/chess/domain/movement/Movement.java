package chess.domain.movement;

import static chess.domain.movement.Continuity.CONTINUOUS;
import static chess.domain.movement.Continuity.DISCONTINUOUS;
import static chess.domain.movement.Direction.DIAGONAL;
import static chess.domain.movement.Direction.L_SHAPE;
import static chess.domain.movement.Direction.STRAIGHT;

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
        Direction direction =  Direction.from(fileInterval, rankInterval);
        return Arrays.stream(Movement.values())
                .filter(movement -> movement.continuity == continuity && movement.direction == direction)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 움직임입니다."));
    }
}
