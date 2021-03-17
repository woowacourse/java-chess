package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.List;

public enum MoveDirection {
    UP(Arrays.asList(0, 1), "line"),
    DOWN(Arrays.asList(0, -1) ,"line"),
    LEFT(Arrays.asList(-1, 0), "line"),
    RIGHT(Arrays.asList(1, 0), "line"),
    LEFT_UP(Arrays.asList(-1, 1), "diagonal"),
    RIGHT_UP(Arrays.asList(1, 1), "diagonal"),
    LEFT_DOWN(Arrays.asList(-1, -1), "diagonal"),
    RIGHT_DOWN(Arrays.asList(1, -1), "diagonal");

    private List<Integer> vector;
    private String moveType;

    MoveDirection(List<Integer> vector, String moveType) {
        this.vector = vector;
        this.moveType = moveType;
    }

    public static MoveDirection getDirection(Position source, Position target) {
        int horizontalDistance = source.computeHorizontalDistance(target);
        int verticalDistance = source.computeVerticalDistance(target);
        isLineOrDiagonal(horizontalDistance, verticalDistance);
        int horizontalVector = getVectorValue(horizontalDistance);
        int verticalVector = getVectorValue(verticalDistance);

        List<Integer> vectors = Arrays.asList(horizontalVector, verticalVector);
        return matchVectors(vectors);
    }

    private static MoveDirection matchVectors(List<Integer> vectors) {
        return Arrays.stream(values())
            .filter(element -> element.vector.equals(vectors))
            .findAny()
            .orElseThrow(() -> new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE));
    }

    private static int getVectorValue(int distance) {
        if (distance == 0) {
            return 0;
        }
        return distance / Math.abs(distance);
    }

    private static void isLineOrDiagonal(int horizontalDistance, int verticalDistance) {
        if (Math.abs(horizontalDistance) != Math.abs(verticalDistance) &&
            (horizontalDistance != 0 && verticalDistance != 0)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    public int getXVector() {
        return vector.get(0);
    }

    public int getYVector() {
        return vector.get(1);
    }
}
