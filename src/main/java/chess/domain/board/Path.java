package chess.domain.board;

import java.util.List;

public class Path {
    public static final String NOT_MOVABLE_DIRECTION_ERROR = "이동할 수 없는 방향입니다.";

    private Path() {
    }

    static Direction findDirection(ChessBoard chessBoard, Position sourcePosition, Position targetPosition) {
        List<Direction> directions = chessBoard.getCandidateDirections(sourcePosition);
        return directions.stream()
                .filter(direction -> isMovableDirection(sourcePosition, targetPosition, direction))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_MOVABLE_DIRECTION_ERROR));
    }

    private static boolean isMovableDirection(Position sourcePosition, Position targetPosition, Direction direction) {
        Position currentPosition = sourcePosition;
        while (currentPosition.hasNextPosition(direction)) {
            currentPosition = currentPosition.nextPosition(direction);
            if (currentPosition.equals(targetPosition)) {
                return true;
            }
        }
        return false;
    }
}
