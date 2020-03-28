package chess.domain.piece.implementation;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDirectionPiece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Queen extends MoveByDirectionPiece {

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

    private Queen(Position position, Team team) {
        super(PieceType.QUEEN, position, team);
    }

    public static Queen of(Position position, Team team) {
        return new Queen(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Queen(target, team);
    }
}
