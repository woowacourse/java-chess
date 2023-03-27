package chess.piece;

import chess.board.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private static final int INITIAL_MOVE_COUNT = 2;
    private static final int NOT_INITIAL_MOVE_COUNT = 1;
    private static final int WHITE_RANK_VALUE = 2;
    private static final int BLACK_RANK_VALUE = 7;

    private final List<Direction> directions;

    public Pawn(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        if (this.side == Side.WHITE) {
            directions.addAll(List.of(Direction.UP, Direction.UP_RIGHT, Direction.UP_LEFT));
        }
        if (this.side == Side.BLACK) {
            directions.addAll(List.of(Direction.DOWN, Direction.DOWN_RIGHT, Direction.DOWN_LEFT));
        }
        return directions;
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == INITIAL_MOVE_COUNT) {
            return direction == Direction.UP || direction == Direction.DOWN;
        }

        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = moveCount == NOT_INITIAL_MOVE_COUNT;

        return isPossibleDirection && isPossibleDistance;
    }

    private boolean isStartPosition() {
        final boolean isWhiteStartPosition = position.getRank() == WHITE_RANK_VALUE && side == Side.WHITE;
        final boolean isBlackStartPosition = position.getRank() == BLACK_RANK_VALUE && side == Side.BLACK;
        return isWhiteStartPosition || isBlackStartPosition;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Pawn(positionToMove, this.side);
    }

    @Override
    public boolean isNeedToCheckWhenDiagonalMove() {
        return true;
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == INITIAL_MOVE_COUNT) {
            return List.of(position.getNextPosition(direction));
        }
        return Collections.emptyList();
    }
}
