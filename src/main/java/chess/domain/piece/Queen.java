package chess.domain.piece;

import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;
import chess.exception.ObstacleOnPathException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    protected void validateMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (!MOVING_DIRECTIONS.contains(movingDirection)) {
            throw new MovingDirectionException();
        }

        Position startPathPosition = position.moveByDirection(movingDirection);
        while (!startPathPosition.equals(target)) {
            checkObstacleOnPath(boardDto, startPathPosition);
            startPathPosition = startPathPosition.moveByDirection(movingDirection);
        }

    }

    private void checkObstacleOnPath(final Map<Position, PieceDto> boardDto, final Position startPathPosition) {
        if (!Objects.isNull(boardDto.get(startPathPosition))) {
            throw new ObstacleOnPathException();
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
