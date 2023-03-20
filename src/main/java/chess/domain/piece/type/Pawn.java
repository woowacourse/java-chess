package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.List;

public class Pawn extends Piece {

    public static final String DIRECTION_ERROR_MESSAGE = "Pawn이 이동할 수 있는 방향이 아닙니다";
    public static final String DISTANCE_ERROR_MESSAGE = "Pawn이 한 번에 이동할 수 있는 거리가 아닙니다";
    private static final String MOVE_FORWARD_ERROR_MESSAGE = "Pawn은 도착점에 기물이 있으면 앞으로 이동할 수 없습니다";
    private static final String MOVE_DIAGONAL_ERROR_MESSAGE = "Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다";
    private static final int MAXIMUM_DISTANCE_WHEN_FIRST_MOVE = 2;
    private static final int MAXIMUM_DISTANCE_AFTER_FIRST_MOVE = 1;
    private final List<Direction> movableDirection;
    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
        this.movableDirection = createMovableDirectionByColor(color);
    }

    private List<Direction> createMovableDirectionByColor(Color color) {
        if (color == Color.BLACK) {
            return List.of(Direction.BOTTOM, Direction.BOTTOM_LEFT,
                    Direction.BOTTOM_RIGHT);
        }
        return List.of(Direction.TOP, Direction.TOP_LEFT,
                Direction.TOP_RIGHT);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = Direction.findDirectionByGap(start, end);

        checkMovableDirection(direction);
        checkMovableDistance(start, end);
        checkMovableToDestination(colorOfDestination, direction);
        isFirstMove = false;
        return true;
    }

    protected void checkMovableDirection(Direction direction) {
        if (!movableDirection.contains(direction)) {
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    private void checkMovableDistance(Position start, Position end) {
        int distanceOfColumns = Math.abs(start.findGapOfColumn(end));
        int distanceOfRanks = Math.abs(start.findGapOfRank(end));
        if (isFirstMove) {
            checkMovableDistanceWhenFirstMove(distanceOfColumns, distanceOfRanks);
            return;
        }
        checkMovableDistanceAfterFirstMove(distanceOfColumns, distanceOfRanks);
    }

    private void checkMovableDistanceWhenFirstMove(int distanceOfColumns, int distanceOfRanks) {
        if (distanceOfColumns > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE
                || distanceOfRanks > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
    }

    private static void checkMovableDistanceAfterFirstMove(int distanceOfColumns, int distanceOfRanks) {
        if (distanceOfColumns > MAXIMUM_DISTANCE_AFTER_FIRST_MOVE
                || distanceOfRanks > MAXIMUM_DISTANCE_AFTER_FIRST_MOVE) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
    }

    private void checkMovableToDestination(Color colorOfDestination, Direction direction) {
        if (isForwardDirection(direction)) {
            checkMoveForward(colorOfDestination);
        }
        if (isDiagonalDirection(direction)) {
            checkMoveDiagonal(colorOfDestination);
        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    private void checkMoveForward(Color colorOfDestination) {
        if (colorOfDestination != Color.NONE) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_MESSAGE);
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }


    private void checkMoveDiagonal(Color colorOfDestination) {
        if (colorOfDestination == Color.NONE || this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_MESSAGE);
        }
    }
}
