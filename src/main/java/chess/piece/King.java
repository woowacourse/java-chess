package chess.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.board.Position;

public class King extends Piece {

    private final List<Direction> directions;

    private static final int KING_MOVE_COUNT = 1;

    public King(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = position.getMoveCount(targetPosition, direction) == KING_MOVE_COUNT;
        return isPossibleDistance && isPossibleDirection;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new King(positionToMove, this.side);
    }

    @Override
    public boolean isNeedToCheckWhenDiagonalMove() {
        return false;
    }

    @Override
    public List<Position> getPaths(Position targetPosition) {
        List<Position> paths = new ArrayList<>();
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCountBeforeArrivalPosition = position.getMoveCount(targetPosition, direction) - 1;
        Position nextPosition = this.position;
        for (int next = 0; next < moveCountBeforeArrivalPosition; next++) {
            nextPosition = nextPosition.getNextPosition(direction);
            paths.add(nextPosition);
        }
        return paths;
    }
}
