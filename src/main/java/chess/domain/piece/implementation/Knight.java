package chess.domain.piece.implementation;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDistancePiece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends MoveByDistancePiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH_NORTH_EAST,
                MovingDirection.NORTH_EAST_EAST,
                MovingDirection.SOUTH_EAST_EAST,
                MovingDirection.SOUTH_SOUTH_EAST,
                MovingDirection.SOUTH_SOUTH_WEST,
                MovingDirection.SOUTH_WEST_WEST,
                MovingDirection.NORTH_WEST_WEST,
                MovingDirection.NORTH_NORTH_WEST
        );
    }

    private Knight(Position position, Team team) {
        super(PieceType.KNIGHT, position, team);
    }

    public static Knight of(Position position, Team team) {
        return new Knight(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Knight(target, team);
    }
}
