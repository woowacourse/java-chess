package chess.domain.piece;

import chess.domain.*;

import java.util.List;

public class
Knight extends ChessPiece {

    public static final List<Direction> MOVABLE_DIRECTION = List.of(Direction.NORTH_NORTH_EAST, Direction.NORTH_NORTH_WEST, Direction.NORTH_EAST_EAST, Direction.NORTH_WEST_WEST,
            Direction.SOUTH_SOUTH_EAST, Direction.SOUTH_SOUTH_WEST, Direction.SOUTH_EAST_EAST, Direction.SOUTH_WEST_WEST);
    public static final int MAX_MOVEMENT_OF_KNIGHT = 1;

    public Knight(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_KNIGHT;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_KNIGHT;
        }
        return Shape.BLANK;
    }

    @Override
    Direction findMovableDirection(Position sourcePosition, Position targetPosition) {
        if (Direction.isMovableNorthNorthEast(sourcePosition, targetPosition)) {
            return Direction.NORTH_NORTH_EAST;
        }
        if (Direction.isMovableNorthNorthWest(sourcePosition, targetPosition)) {
            return Direction.NORTH_NORTH_WEST;
        }
        if (Direction.isMovableNorthEastEast(sourcePosition, targetPosition)) {
            return Direction.NORTH_EAST_EAST;
        }
        if (Direction.isMovableNorthWestWest(sourcePosition, targetPosition)) {
            return Direction.NORTH_WEST_WEST;
        }
        if (Direction.isMovableSouthSouthEast(sourcePosition, targetPosition)) {
            return Direction.SOUTH_SOUTH_EAST;
        }
        if (Direction.isMovableSouthSouthWest(sourcePosition, targetPosition)) {
            return Direction.SOUTH_SOUTH_WEST;
        }
        if (Direction.isMovableSouthEastEast(sourcePosition, targetPosition)) {
            return Direction.SOUTH_EAST_EAST;
        }
        if (Direction.isMovableSouthWestWest(sourcePosition, targetPosition)) {
            return Direction.SOUTH_WEST_WEST;
        }
        throw new IllegalArgumentException("[ERROR] 나이트의 이동 경로에 해당하지 않습니다.");
    }

    @Override
    int findDistance(Direction direction, Position sourcePosition, Position targetPosition) {
        validateDirection(direction);
        int distance = 0;
        while (sourcePosition != targetPosition) {
            sourcePosition = sourcePosition.getMovingPosition(direction);
            distance++;
            validateDistance(distance);
        }
        return distance;
    }

    public void validateDirection(Direction direction) {
        if (!MOVABLE_DIRECTION.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 방향으로는 이동할 수 없습니다.");
        }
    }

    private void validateDistance(int distance) {
        if (distance > MAX_MOVEMENT_OF_KNIGHT) {
            throw new IllegalArgumentException("[ERROR] Knight는 한 번만 이동할 수 있습니다.");
        }
    }

    @Override
    public boolean isMovable(Movement movement, ChessBoard chessBoard) {
        Position targetPosition = movement.findTargetPosition();
        if (chessBoard.isEmpty(targetPosition)) {
            return true;
        }
        return !hasSameColor(chessBoard.getChessPiece(targetPosition));
    }
}
