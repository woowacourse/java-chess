package chess.domain.board;

import java.util.List;

public class Path {

    private static final String NOT_MOVABLE_DIRECTION = "이동할 수 없는 방향입니다.";
    private static final int LINEAR_DIRECTION_DEGREE = 0;

    private Path() {
    }

    public static boolean isMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare) {
        Direction direction = movableDirection(chessBoard, sourceSquare, targetSquare);
        if (sourceSquare.isPawn()) {
            return isPawnMovable(chessBoard, sourceSquare, targetSquare, direction);
        }
        if (sourceSquare.isIterable()) {
            return isIterableMovable(chessBoard, sourceSquare, targetSquare, direction);
        }
        return isNonIterableMovable(chessBoard, sourceSquare, targetSquare, direction);
    }

    private static Direction movableDirection(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare) {
        List<Direction> directions = sourceSquare.getDirections();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            if (isMovableDirection(chessBoard, sourceSquare, targetSquare, xDegree, yDegree)) {
                return direction;
            }
        }
        throw new IllegalArgumentException(NOT_MOVABLE_DIRECTION);
    }

    private static boolean isMovableDirection(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, int xDegree, int yDegree) {
        Square currentSquare = sourceSquare;
        while (currentSquare.hasNextPossibleSquare(xDegree, yDegree)) {
            Square nextSquare = chessBoard
                .getSquare(currentSquare.getPosition().nextPosition(xDegree, yDegree));
            if (nextSquare.equals(targetSquare)) {
                return true;
            }
            currentSquare = nextSquare;
        }
        return false;
    }

    private static boolean isIterableMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        Square currentSquare = sourceSquare;
        while (!currentSquare.equals(targetSquare)) {
            Square nextSquare = chessBoard
                .getSquare(currentSquare.getPosition()
                    .nextPosition(direction.getXDegree(), direction.getYDegree()));
            if (!nextSquare.isBlank()) {
                return false;
            }
            currentSquare = nextSquare;
        }
        return currentSquare.isNotSameColor(targetSquare);
    }

    private static boolean isNonIterableMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        Square nextSquare = chessBoard
            .getSquare(sourceSquare.getPosition()
                .nextPosition(direction.getXDegree(), direction.getYDegree()));
        return nextSquare.isNotSameColor(nextSquare) && nextSquare.equals(targetSquare);
    }

    private static boolean isPawnMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        int xDegree = direction.getXDegree();
        int yDegree = direction.getYDegree();
        Square nextSquare = chessBoard
            .getSquare(sourceSquare.getPosition()
                .nextPosition(xDegree, yDegree));
        if (direction.getXDegree() == LINEAR_DIRECTION_DEGREE) {
            Square doubleNextSquare = chessBoard
                .getSquare(nextSquare.getPosition()
                    .nextPosition(xDegree, yDegree));
            return linearPawnMove(sourceSquare, targetSquare, nextSquare, doubleNextSquare);
        }
        return !nextSquare.isBlank() && sourceSquare.isNotSameColor(nextSquare) && nextSquare
            .equals(targetSquare);
    }

    private static boolean linearPawnMove(Square sourceSquare, Square targetSquare,
        Square nextSquare, Square doubleNextSquare) {
        if (!nextSquare.isBlank()) {
            return false;
        }
        if (nextSquare.equals(targetSquare)) {
            return true;
        }
        if (sourceSquare.isStartingPosition()) {
            return doubleNextSquare.isBlank() && doubleNextSquare.equals(targetSquare);
        }
        return false;
    }
}
