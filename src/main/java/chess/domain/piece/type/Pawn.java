package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public static final String DIRECTION_ERROR_MESSAGE = "Pawn이 이동할 수 있는 방향이 아닙니다";
    public static final String DISTANCE_ERROR_MESSAGE = "Pawn이 한 번에 이동할 수 있는 거리가 아닙니다";
    private static final String MOVE_FORWARD_ERROR_MESSAGE = "Pawn은 도착점에 기물이 있으면 앞으로 이동할 수 없습니다";
    private static final String MOVE_DIAGONAL_ERROR_MESSAGE = "Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다";
    private static final int MAXIMUM_DISTANCE_WHEN_FIRST_MOVE = 2;
    private static final int MAXIMUM_DISTANCE_AFTER_FIRST_MOVE = 1;
    private final List<Direction> movableDirection;

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

    protected void checkMovableDirection(Direction direction) {
        if (!movableDirection.contains(direction)) {
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
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

    @Override
    public void checkMovable(Position start, Position end, Color destinationColor) {
        Direction direction = Direction.findDirectionByGap(start, end);
        System.out.println(direction);
        if (!movableDirection.contains(direction)) {
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }

        List<Position> route = findRoute(start, end);
        if (isFirstMove(start) && route.size() > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
        if (!isFirstMove(start) && route.size() > MAXIMUM_DISTANCE_AFTER_FIRST_MOVE) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }

        if (isForwardDirection(direction) && destinationColor != Color.NONE) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_MESSAGE);
        }
        if (isDiagonalDirection(direction) && destinationColor != getColor().getOpponent()) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Position> findRoute(Position start, Position end) {
        List<Position> route = new ArrayList<>();
        Direction direction = Direction.findDirectionByGap(start, end);
        Position currentPosition = start;

        do {
            currentPosition = currentPosition.moveDirection(direction);
            route.add(currentPosition);
        } while (!currentPosition.equals(end));
        return route;
    }

    private boolean isFirstMove(Position start) {
        if(getColor()== Color.BLACK && start.getRank().getIndex() == 7) {
            return true;
        }
        if(getColor() == Color.WHITE && start.getRank().getIndex() == 2) {
            return true;
        }
        return false;
    }
}
