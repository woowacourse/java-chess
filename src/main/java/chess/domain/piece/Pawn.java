package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;

public class Pawn extends Piece {

    private static final String name = "p";
    private static final String MOVE_FORWARD_ERROR_GUIDE_MESSAGE = "도착점에 기물이 있어 Pawn은 앞으로 이동할 수 없습니다";
    private static final String MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE = "Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다";
    private static final int MAXIMUM_DISTANCE_WHEN_FIRST_MOVE = 2;
    private static final int MAXIMUM_DISTANCE_AFTER_FIRST_MOVE = 1;
    private final List<Direction> movableDirection;
    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(name, color);
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
        Direction direction = findDirectionToMove(start, end);
        checkMovableDirection(direction);
        checkMovableAtOnce(start, end);
        checkMovableToDestination(colorOfDestination, direction);
        return true;
    }

    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException("pawn이 이동할 수 있는 방향이 아닙니다");
        }
    }

    public void checkMovableAtOnce(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColum(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));

        if (isFirstMove
                && absGapOfColumn > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE
                && absGapOfRank > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE) {
            throw new IllegalArgumentException("pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }

        if(!isFirstMove
                && absGapOfColumn > MAXIMUM_DISTANCE_AFTER_FIRST_MOVE
                && absGapOfRank > MAXIMUM_DISTANCE_AFTER_FIRST_MOVE){
            throw new IllegalArgumentException("pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }
    }

    private void checkMovableToDestination(Color colorOfDestination, Direction direction) {
        if(isForwardDirection(direction)) {
            checkMoveForward(colorOfDestination);
        }

        if(isDiagonalDirection(direction)){
            checkMoveDiagonal(colorOfDestination);
        }
    }

    public void checkMoveForward(Color colorOfDestination) {
        if (colorOfDestination != Color.NONE) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_GUIDE_MESSAGE);
        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    public void checkMoveDiagonal(Color colorOfDestination) {
        if (colorOfDestination == Color.NONE || this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE);
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }

}
