package chess.domain.piece;

import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class King extends UnchangeablePiece {

    private static final String BLACK_KING_UNICODE = "\u265A";
    private static final String WHITE_KING_UNICODE = "\u2654";
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

    private King(Position position, Player player) {
        super(PieceType.KING, position, player);
    }

    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    protected void validateMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (!MOVING_DIRECTIONS.contains(movingDirection)) {
            throw new MovingDirectionException();
        }
        int fileDifference = position.getFileDifference(target);
        int rankDifference = position.getRankDifference(target);
        if (!(movingDirection.getFileDirection() == fileDifference &&
                movingDirection.getRankDirection() == rankDifference)) {
            throw new MovingDirectionException();
        }
    }

    @Override
    public String getFigure() {
        if (player == Player.BLACK) {
            return BLACK_KING_UNICODE;
        }
        return WHITE_KING_UNICODE;
    }
}
