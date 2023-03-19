package chess.piece;

import chess.board.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

    private final List<Direction> directions;

    public Queen(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }

    protected List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Queen(positionToMove, this.side);
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
