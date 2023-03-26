package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.List;

import static chess.domain.score.Score.KING_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;

public class King extends Piece {

    private static final int MOVABLE_DISTANCE = 1;

    private final List<Direction> direction = List.of(Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT, Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public King(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), KING_SCORE.getScore());
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

    @Override
    public int calculateKing(int count) {
        return count + 1;
    }

    @Override
    public int calculatePawn(int count, Color color) {
        return count;
    }

    @Override
    public boolean findDirection(Direction direction, Position start, Position end, Piece piece) {
        int gapOfRank = start.findGapOfRank(end);
        int gapOfColumn = start.findGapOfColum(end);
        int absX = Math.abs(gapOfColumn);
        int absY = Math.abs(gapOfRank);

        if (isDiagonal(direction)) {
            return direction.getX() * absX == gapOfColumn && direction.getY() * absX == gapOfRank;
        }

        return direction.getX() * absX == gapOfColumn && direction.getY() * absY == gapOfRank;
    }
}
