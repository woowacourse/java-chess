package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.List;
import java.util.Map;

import static chess.domain.score.Score.KNIGHT_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;

public class Knight extends Piece {

    private static final Map<Integer, Integer> distance = Map.of(1, 2, 2, 1);

    private static final List<Direction> direction = Direction.getKnightDirection();

    public Knight(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), KNIGHT_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = Direction.findDirectionByGap(start, end, this);
        checkDirection(direction);
        checkDistance(start, end);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    private void checkDistance(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColum(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));

        if (!isMovableAtOnce(absGapOfColumn, absGapOfRank)) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isMovableAtOnce(int absGapOfColumn, int absGapOfRank) {
        for (Map.Entry<Integer, Integer> entry : distance.entrySet()) {
            if (entry.getKey() == absGapOfColumn && entry.getValue() == absGapOfRank) {
                return true;
            }
        }
        return false;
    }

    private void checkDirection(Direction direction) {
        if (!Knight.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    @Override
    public int calculateKing(int count) {
        return count;
    }

    @Override
    public int calculatePawn(int count, Color color) {
        return count;
    }

    @Override
    public boolean findDirection(Direction direction, Position start, Position end, Piece piece) {
        int gapOfRank = start.findGapOfRank(end);
        int gapOfColumn = start.findGapOfColum(end);

        return direction.getX() == gapOfColumn && direction.getY() == gapOfRank;
    }
}
