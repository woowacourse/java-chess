package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Color;
import chess.domain.Position;

import java.util.List;

import static chess.domain.piece.PieceName.KING_NAME;
import static chess.domain.score.Score.KING_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;

public class King extends Piece {

    private static final int MOVABLE_DISTANCE = 1;

    private final List<Direction> direction = List.of(Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT, Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public King(Color color) {
        super(KING_NAME.getName(), color, KING_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color destinationColor) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkDistance(start, end);
        checkMovableToDestination(destinationColor);
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

        if (absGapOfColumn > MOVABLE_DISTANCE || absGapOfRank > MOVABLE_DISTANCE) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }
}
