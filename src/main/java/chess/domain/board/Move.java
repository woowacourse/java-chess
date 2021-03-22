package chess.domain.board;

public class Move {

    private static final int LINEAR_DIRECTION_DEGREE = 0;

    private Move() {
    }

    public static boolean isMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare) {
        Direction direction = Direction
            .findDirection(sourceSquare.getPosition(), targetSquare.getPosition());
        if (!sourceSquare.getDirections().contains(direction)) {
            throw new IllegalArgumentException(Direction.NOT_MOVABLE_DIRECTION);
        }
        if (sourceSquare.isPawn()) {
            return isPawnMovable(chessBoard, sourceSquare, targetSquare, direction);
        }
        if (sourceSquare.isIterable()) {
            return isIterableMovable(chessBoard, sourceSquare, targetSquare, direction);
        }
        return isNonIterableMovable(chessBoard, sourceSquare, targetSquare, direction);
    }

    private static boolean isIterableMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        Square nextSquare = chessBoard
            .getSquare(sourceSquare.nextPosition(direction));
        while (!nextSquare.equals(targetSquare)) {
            if (!nextSquare.isBlank()) {
                return false;
            }
            nextSquare = chessBoard.getSquare(nextSquare.nextPosition(direction));
        }
        return nextSquare.isNotSameColor(sourceSquare);
    }

    private static boolean isNonIterableMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        Square nextSquare = chessBoard
            .getSquare(sourceSquare.nextPosition(direction));
        return nextSquare.isNotSameColor(sourceSquare) && nextSquare.equals(targetSquare);
    }

    private static boolean isPawnMovable(ChessBoard chessBoard, Square sourceSquare,
        Square targetSquare, Direction direction) {
        Square nextSquare = chessBoard
            .getSquare(sourceSquare.nextPosition(direction));
        if (direction.getXDegree() == LINEAR_DIRECTION_DEGREE) {
            Square doubleNextSquare = chessBoard.getSquare(nextSquare.nextPosition(direction));
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
