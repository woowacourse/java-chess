package chess.domain;

import chess.domain.piece.Empty;

public class Movement {

    private final Position sourcePosition;
    private final Direction direction;
    private final int distance;

    public Movement(Position sourcePosition, Direction direction, int distance) {
        this.sourcePosition = sourcePosition;
        this.direction = direction;
        this.distance = distance;
    }

    public static Movement generateRookMovement(Position sourcePosition, Direction direction, int distance) {
        if (!Direction.isRookDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 룩은 해당 방향으로 이동할 수 없습니다.");
        }

        return new Movement(sourcePosition, direction, distance);
    }

    public Position findTargetPosition() {
        Position tempPosition = sourcePosition;
        for (int step = 0; step < distance; step++) {
            tempPosition = tempPosition.getMovingPosition(direction);
        }
        tempPosition.validateBorderOfChessBoard(tempPosition);
        return tempPosition;
    }

    public boolean hasObstacleInRoute(ChessBoard chessBoard) {
        Position tempPosition = this.sourcePosition;
        for (int i = 0; i < distance - 1; i++) {
            tempPosition = tempPosition.getMovingPosition(direction);
            if (!chessBoard.getChessBoard().get(tempPosition).equals(new Empty())) {
                return true;
            }
        }
        return false;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDistance() {
        return distance;
    }
}
