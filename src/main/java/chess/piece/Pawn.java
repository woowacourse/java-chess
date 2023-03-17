package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    public static final int FIRST_MOVE_TWO_SPACES = 2;

    private final List<Direction> directions;
    private String name = "p";

    public Pawn(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
        if (side == Side.BLACK) {
            name = name.toUpperCase();
        }
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        if (this.side == Side.WHITE) {
            initWhiteSideDirections(directions);
        }
        if (this.side == Side.BLACK) {
            initBlackSideDirections(directions);
        }
        return directions;
    }

    private void initWhiteSideDirections(final List<Direction> directions) {
        directions.add(Direction.UP);
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
    }

    private void initBlackSideDirections(final List<Direction> directions) {
        directions.add(Direction.DOWN);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == FIRST_MOVE_TWO_SPACES) {
            return direction == Direction.UP || direction == Direction.DOWN;
        }

        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = moveCount == 1;

        return isPossibleDirection && isPossibleDistance;
    }

    private boolean isStartPosition() {
        final boolean isWhiteStartPosition = position.getRank() == 2 && side == Side.WHITE;
        final boolean isBlackStartPosition = position.getRank() == 7 && side == Side.BLACK;
        return isWhiteStartPosition || isBlackStartPosition;
    }

    @Override
    public Pawn move(final Position positionToMove) {
        return new Pawn(positionToMove, this.side);
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == FIRST_MOVE_TWO_SPACES) {
            return List.of(position.getNextPosition(direction));
        }
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return name;
    }
}
