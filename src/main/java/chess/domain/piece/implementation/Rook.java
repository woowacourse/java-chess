package chess.domain.piece.implementation;

import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveByDirectionPiece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Rook extends MoveByDirectionPiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                MovingDirection.NORTH,
                MovingDirection.EAST,
                MovingDirection.SOUTH,
                MovingDirection.WEST
        );
    }

    private Rook(Position position, Team team) {
        super(PieceType.ROOK, position, team);
    }

    public static Rook of(Position position, Team team) {
        return new Rook(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Rook(target, team);
    }
}
