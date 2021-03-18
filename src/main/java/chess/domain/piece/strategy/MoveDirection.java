package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.List;

public enum MoveDirection {
    UP(Arrays.asList(0, 1)),
    DOWN(Arrays.asList(0, -1)),
    LEFT(Arrays.asList(-1, 0)),
    RIGHT(Arrays.asList(1, 0)),
    LEFT_UP(Arrays.asList(-1, 1)),
    RIGHT_UP(Arrays.asList(1, 1)),
    LEFT_DOWN(Arrays.asList(-1, -1)),
    RIGHT_DOWN(Arrays.asList(1, -1));

    private List<Integer> vector;

    MoveDirection(List<Integer> vector) {
        this.vector = vector;
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

    public static Boolean isWhiteForward(MoveDirection moveDirection) {
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP).contains(moveDirection);
    }

    public static Boolean isBlackForward(MoveDirection moveDirection) {
        return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN).contains(moveDirection);
    }

    public int getXVector() {
        return vector.get(0);
    }

    public int getYVector() {
        return vector.get(1);
    }
}
