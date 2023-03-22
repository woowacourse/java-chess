package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.direction.Direction;

import java.util.List;

import static chess.domain.piece.PieceName.BISHOP_NAME;
import static chess.domain.score.Score.BISHOP_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;

public class Bishop extends Piece {

    private static final List<Direction> direction = List.of(
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public Bishop(Color color) {
        super(BISHOP_NAME.getName(), color,BISHOP_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkDirection(Direction direction) {
        if (!Bishop.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }
}
