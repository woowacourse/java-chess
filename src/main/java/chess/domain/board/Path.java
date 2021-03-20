package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {

    public static final int LINEAR_DIRECTION_DEGREE = 0;

    private Path() {}

    public static List<Square> getMovableSquares(ChessBoard chessBoard, Square square) {
        List<Square> movableSquares = new ArrayList<>();
        List<Direction> directions = square.getDirections();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movableSquares.addAll(moveByType(chessBoard, square, xDegree, yDegree));
        }
        return movableSquares;

    }

    private static List<Square> moveByType(ChessBoard chessBoard, Square square, int xDegree, int yDegree) {
        List<Square> movableSquares = new ArrayList<>();
        if (square.isIterable()) {
            movableSquares.addAll(iterableSquare(chessBoard, square, xDegree, yDegree));
        }
        if (!square.isIterable() && !square.isPawn()) {
            movableSquares.addAll(nonIterableSquare(chessBoard, square, xDegree, yDegree));
        }
        if (!square.isIterable() && square.isPawn()) {
            movableSquares.addAll(pawnMovableSquare(chessBoard, square, xDegree, yDegree));
        }
        return Collections.unmodifiableList(movableSquares);
    }

    private static List<Square> iterableSquare(ChessBoard chessBoard, Square square, int xDegree, int yDegree) {
        List<Square> movableSquares = new ArrayList<>();
        Square currentSquare = square;
        while (currentSquare.hasNextPossibleSquare(xDegree, yDegree)) {
            Square nextSquare = chessBoard
                .getSquare(currentSquare.getPosition().nextPosition(xDegree, yDegree));
            if (nextSquare.isBlank()) {
                movableSquares.add(nextSquare);
            }
            if (!nextSquare.isBlank() && currentSquare.isNotSameColor(nextSquare)) {
                movableSquares.add(nextSquare);
                break;
            }
            currentSquare = nextSquare;
        }
        return Collections.unmodifiableList(movableSquares);
    }

    private static List<Square> nonIterableSquare(ChessBoard chessBoard, Square square, int xDegree, int yDegree) {
        List<Square> movableSquares = new ArrayList<>();
        if (square.hasNextPossibleSquare(xDegree, yDegree)) {
            Square nextSquare = chessBoard
                .getSquare(square.getPosition().nextPosition(xDegree, yDegree));
            if (square.isNotSameColor(nextSquare)) {
                movableSquares.add(nextSquare);
            }
        }
        return Collections.unmodifiableList(movableSquares);
    }

    private static List<Square> pawnMovableSquare(ChessBoard chessBoard, Square square, int xDegree, int yDegree) {
        List<Square> movableSquares = new ArrayList<>();
        if (square.hasNextPossibleSquare(xDegree, yDegree)) {
            Square nextSquare = chessBoard
                .getSquare(square.getPosition().nextPosition(xDegree, yDegree));
            if (xDegree == LINEAR_DIRECTION_DEGREE) {
                if (nextSquare.isBlank()) {
                    movableSquares.add(nextSquare);
                }
                if (square.isStartingPosition()) {
                    nextSquare = chessBoard
                        .getSquare(nextSquare.getPosition().nextPosition(xDegree, yDegree));
                    if (nextSquare.isBlank()) {
                        movableSquares.add(nextSquare);
                    }
                }
                return Collections.unmodifiableList(movableSquares);
            }
            if (!nextSquare.isBlank() && square.isNotSameColor(nextSquare)) {
                movableSquares.add(nextSquare);
            }
        }
        return Collections.unmodifiableList(movableSquares);
    }
}
