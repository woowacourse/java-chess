package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Queen extends Piece {

    private static final String BLACK_QUEEN_UNICODE = "\u265B";
    private static final String WHITE_PAWN_UNICODE = "\u2655";
    private static final List<Direction> movingDirection;

    static {
        movingDirection = Arrays.asList(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST
        );
    }

    private Queen(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Queen(position, player);
    }

    @Override
    protected boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto) {
        //        List<Position> paths = new LinkedList<>();
        Direction direction = Direction.getDirection(position, target);

        if (!movingDirection.contains(direction)) {
            return false;
        }

        int fileDirection = direction.getFileDirection();
        int rankDirection = direction.getRankDirection();

        Position startPathPosition = position.getMovedPosition(fileDirection, rankDirection);
        for (; !startPathPosition.equals(target); startPathPosition = startPathPosition.getMovedPosition(fileDirection, rankDirection)) {
            if (!Objects.isNull(boardDto.get(startPathPosition))) {
                return false;
            }
//                paths.add(startPathPosition);
        }
        return true;
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_QUEEN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
