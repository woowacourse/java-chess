package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;

import static chess.domain.piece.PieceName.QUEEN_NAME;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;

public class Queen extends Piece {

    private static final List<Direction> direction = List.of(
            Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);


    public Queen(Color color) {
        super(QUEEN_NAME.getName(), color);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkDirection(Direction direction) {
        if (!Queen.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }
}
