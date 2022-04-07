package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;

public class Pawn extends Piece {

    public static final Row BLACK_INIT_ROW = Row.SEVEN;
    public static final Row WHITE_INIT_ROW = Row.TWO;

    private static final int STRAIGHT_DIRECTION_INDEX = 0;
    private static final int DIAGONAL_DIRECTION_START_INDEX = 1;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Position fromPosition, Position toPosition) {
        Direction directionByPositions = Direction.getDirectionByPositions(fromPosition, toPosition);
        List<Direction> movableDirections = getMovableDirections();

        if (directionByPositions.isSameDirection(getStraightDirection(movableDirections))) {
            return checkFirstMove(fromPosition, toPosition);
        }
        if (directionByPositions.isInDirections(getDiagonalDirections(movableDirections))) {
            return directionByPositions.isSameWithDistanceAndDirection(fromPosition, toPosition);
        }
        return false;
    }

    @Override
    protected List<Direction> getMovableDirections() {
        return Direction.pawnDirection(color);
    }

    private Direction getStraightDirection(List<Direction> movableDirections) {
        return movableDirections.get(STRAIGHT_DIRECTION_INDEX);
    }

    private boolean checkFirstMove(Position fromPosition, Position toPosition) {
        int distance = Math.abs(fromPosition.getRowDifferenceWithTarget(toPosition));
        if (isFirstMove(fromPosition)) {
            return distance <= 2;
        }
        return distance == 1;
    }

    private List<Direction> getDiagonalDirections(List<Direction> movableDirections) {
        return movableDirections.subList(DIAGONAL_DIRECTION_START_INDEX,
                movableDirections.size());
    }

    private boolean isFirstMove(Position position) {
        return isBlackFirstMovePawn(position) || isWhiteFirstMovePawn(position);
    }

    private boolean isBlackFirstMovePawn(Position position) {
        return color.isBlack() && position.isInRow(BLACK_INIT_ROW);
    }

    private boolean isWhiteFirstMovePawn(Position position) {
        return color.isWhite() && position.isInRow(WHITE_INIT_ROW);
    }
}
