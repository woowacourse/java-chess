package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.List;

import static chess.direction.Direction.getDiagonalDirection;
import static chess.domain.score.Score.BISHOP_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;

public class Bishop extends Piece {

    private static final List<Direction> direction = getDiagonalDirection();

    public Bishop(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), BISHOP_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color destinationColor) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkMovableToDestination(destinationColor);
        return true;
    }
    private void checkDirection(Direction direction) {
        if (!Bishop.direction.contains(direction)) {
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
    public int calculatePawn(int count,Color color) {
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
