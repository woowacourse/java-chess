package chess.domain.piece.implementation.Strategy;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.implementation.MoveByDistanceStrategy;
import chess.domain.player.Team;

import java.util.Arrays;
import java.util.List;

public class KingStrategy extends MoveByDistanceStrategy {

    private static final List<MovingDirection> DIRECTIONS;

    static {
        DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH,
                MovingDirection.EAST,
                MovingDirection.SOUTH,
                MovingDirection.WEST,
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST,
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        );
    }

    public KingStrategy(Team team) {
        super(DIRECTIONS, team);
    }
}
