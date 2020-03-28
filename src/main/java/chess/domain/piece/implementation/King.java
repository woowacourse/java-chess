package chess.domain.piece.implementation;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDistancePiece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends MoveByDistancePiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
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

    private King(Position position, Team team) {
        super(PieceType.KING, position, team);
    }

    public static King of(Position position, Team team) {
        return new King(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new King(target, team);
    }
}
