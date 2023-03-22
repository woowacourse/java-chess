package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;

import static chess.domain.piece.PieceName.PAWN_NAME;
import static chess.view.ErrorMessage.MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_FORWARD_ERROR_GUIDE_MESSAGE;

public class Pawn extends Piece {

    private int movableDistance = 2;

    private final List<Direction> movableDirection;

    public Pawn(Color color) {
        super(PAWN_NAME.getName(), color);
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
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    public void checkMovableAtOnce(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColum(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));

        if (absGapOfColumn > movableDistance
                && absGapOfRank > movableDistance) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
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
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    public void checkMoveDiagonal(Color colorOfDestination) {
        if (colorOfDestination == Color.NONE || this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }

    public void afterFirstMove() {
        movableDistance = 1;
    }
}
