package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Column;
import chess.domain.Position;

import java.util.List;

import static chess.domain.score.Score.PAWN_DEFAULT_SCORE;
import static chess.view.ErrorMessage.MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_FORWARD_ERROR_GUIDE_MESSAGE;

public class Pawn extends Piece {

    private final List<Direction> direction;

    private Column startColumn;

    public Pawn(PieceInfo pieceInfo, Column startColumn) {
        super(pieceInfo.getName(), pieceInfo.getColor(), PAWN_DEFAULT_SCORE.getScore());
        this.direction = createDirectionByColor(pieceInfo.getColor());
        this.startColumn = startColumn;
    }

    private List<Direction> createDirectionByColor(Color color) {
        if (color == Color.BLACK) {
            return List.of(Direction.BOTTOM, Direction.BOTTOM_LEFT,
                    Direction.BOTTOM_RIGHT);
        }
        return List.of(Direction.TOP, Direction.TOP_LEFT,
                Direction.TOP_RIGHT);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color destinationColor) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkDistance(start, end);
        checkMovableToDestination(destinationColor, direction);
        return true;
    }

    private void checkDirection(Direction direction) {
        if (!this.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkDistance(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColum(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));
        int distance = 1;


        if(startColumn.isSameColumn(start.getColumn())){
            distance = 2;
        }

        if (absGapOfColumn > distance || absGapOfRank > distance) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
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

    private void checkMoveForward(Color colorOfDestination) {
        if (colorOfDestination != Color.NONE) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    private void checkMoveDiagonal(Color colorOfDestination) {
        if (colorOfDestination == Color.NONE || this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }
}
