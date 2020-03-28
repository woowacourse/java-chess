package chess.domain.piece.implementation;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDirectionPiece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Bishop extends MoveByDirectionPiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST,
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        );
    }

    private Bishop(Position position, Team team) {
        super(PieceType.BISHOP, position, team);
    }

    public static Bishop of(Position position, Team team) {
        return new Bishop(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Bishop(target, team);
    }
}
