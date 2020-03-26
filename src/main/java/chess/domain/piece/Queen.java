package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;
import chess.exception.ObstacleOnPathException;

import java.util.Arrays;
import java.util.List;

public class Queen extends UnchangeablePiece {

    private static final String BLACK_QUEEN_UNICODE = "\u265B";
    private static final String WHITE_PAWN_UNICODE = "\u2655";
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

    private Queen(Position position, Player player) {
        super(PieceType.QUEEN, position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Queen(position, player);
    }

    @Override
    protected void validateMovingPolicy(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (!MOVING_DIRECTIONS.contains(movingDirection)) {
            throw new MovingDirectionException();
        }

        Position startPathPosition = position.moveByDirection(movingDirection);
        while (!startPathPosition.equals(target)) {
            if (boardState.isNotEmpty(startPathPosition)) {
                throw new ObstacleOnPathException();
            }
            startPathPosition = startPathPosition.moveByDirection(movingDirection);
        }

    }

    @Override
    public String getFigure() {
        if (player == Player.BLACK) {
            return BLACK_QUEEN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
