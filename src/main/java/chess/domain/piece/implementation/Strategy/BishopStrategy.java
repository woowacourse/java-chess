package chess.domain.piece.implementation.Strategy;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.implementation.MoveByDirectionStrategy;
import chess.domain.player.Team;

import java.util.Arrays;
import java.util.List;

public class BishopStrategy extends MoveByDirectionStrategy {

    private static final List<MovingDirection> DIRECTIONS;

    static {
        DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST,
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        );
    }

    public BishopStrategy(Team team) {
        super(DIRECTIONS, team);
    }
}
