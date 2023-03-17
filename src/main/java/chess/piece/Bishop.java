package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private final List<Direction> directions;
    private String name = "b";

    public Bishop(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
        if (side == Side.BLACK) {
            name = name.toUpperCase();
        }
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
        return directions;
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
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

    @Override
    public String getName() {
        return name;
    }
}
