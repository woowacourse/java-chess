package chess.domain.piece;

import chess.domain.*;

import java.util.List;

public class Rook extends ChessPiece {

    private static final List<Direction> MOVABLE_DIRECTION = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);

    public Rook(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_ROOK;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_ROOK;
        }
        return Shape.BLANK;
    }

    @Override
    public Direction findMovableDirection(Position sourcePosition, Position targetPosition) {
        if (Direction.isMovableToEast(sourcePosition, targetPosition)) {
            return Direction.EAST;
        }
        if (Direction.isMovableToWest(sourcePosition, targetPosition)) {
            return Direction.WEST;
        }
        if (Direction.isMovableToSouth(sourcePosition, targetPosition)) {
            return Direction.SOUTH;
        }
        if (Direction.isMovableNorth(sourcePosition, targetPosition)) {
            return Direction.NORTH;
        }
        throw new IllegalArgumentException("[ERROR] 동서남북 중 이동 가능한 방향이 없습니다.");
    }


    @Override
    public int findDistance(Direction direction, Position sourcePosition, Position targetPosition) {
        validateDirection(direction);
        int distance = 0;

        while (sourcePosition != targetPosition) {
            sourcePosition = sourcePosition.getMovingPosition(direction);
            distance++;
        }
        return distance;
    }

    public void validateDirection(Direction direction) {
        if (!MOVABLE_DIRECTION.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 방향으로는 이동할 수 없습니다.");
        }
    }


    @Override
    public boolean isMovable(Movement movement, ChessBoard chessBoard) {
        Position tempPositionInRoute = movement.getSourcePosition();
        if (hasObstacleInRoute(movement, chessBoard, tempPositionInRoute)) {
            return false;
        }
        Position targetPosition = movement.findTargetPosition();
        return canCatch(chessBoard, targetPosition);
    }

    private static boolean hasObstacleInRoute(Movement movement, ChessBoard chessBoard, Position tempPositionInRoute) {
        for (int step = 0; step < movement.getDistance() - 1; step++) {
            tempPositionInRoute = tempPositionInRoute.getMovingPosition(movement.getDirection());
            if (!chessBoard.isEmpty(tempPositionInRoute)) {
                return true;
            }
        }
        return false;
    }

    private boolean canCatch(ChessBoard chessBoard, Position targetPosition) {
        if (chessBoard.isEmpty(targetPosition)) {
            return true;
        }
        return !hasSameColor(chessBoard.getChessPiece(targetPosition));
    }
}
