package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;
import java.util.Map;

import static chess.domain.piece.PieceName.KNIGHT_NAME;
import static chess.domain.score.Score.KNIGHT_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;

public class Knight extends Piece {

    private static final Map<Integer, Integer> distance = Map.of(1, 2, 2, 1);

    private static final List<Direction> direction = List.of(
            Direction.KNIGHT_TOP_LEFT,
            Direction.KNIGHT_TOP_RIGHT,
            Direction.KNIGHT_LEFT_TOP,
            Direction.KNIGHT_LEFT_BOTTOM,
            Direction.KNIGHT_RIGHT_TOP,
            Direction.KNIGHT_RIGHT_BOTTOM,
            Direction.KNIGHT_BOTTOM_LEFT,
            Direction.KNIGHT_BOTTOM_RIGHT);

    public Knight(Color color) {
        super(KNIGHT_NAME.getName(), color, KNIGHT_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = Direction.findDirectionByGap(start, end, this);
        checkDirection(direction);
        checkDistance(start, end);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkDistance(Position start, Position end) {
        int absGapOfColumn = start.findGapOfColum(end);
        int absGapOfRank = start.findGapOfRank(end);

        if (!isMovableAtOnce(absGapOfColumn, absGapOfRank)) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isMovableAtOnce(int absGapOfColumn, int absGapOfRank) {
        return distance.entrySet()
                .stream()
                .anyMatch((entry) -> entry.getKey() == absGapOfColumn && entry.getValue() == absGapOfRank);
    }

    public void checkDirection(Direction direction) {
        if (!Knight.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }
}
