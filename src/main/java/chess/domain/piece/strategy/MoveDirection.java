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

    MoveDirection(final List<Integer> vector) {
        this.vector = vector;
    }

    public static MoveDirection getDirection(final Position source, final Position target) {
        int horizontalDistance = source.computeHorizontalDistance(target);
        int verticalDistance = source.computeVerticalDistance(target);

        int horizontalVector = getVectorValue(horizontalDistance);
        int verticalVector = getVectorValue(verticalDistance);

        List<Integer> vectors = Arrays.asList(horizontalVector, verticalVector);
        return matchVectors(vectors);
    }

    private static MoveDirection matchVectors(final List<Integer> vectors) {
        return Arrays.stream(values())
            .filter(element -> element.vector.equals(vectors))
            .findAny()
            .orElseThrow(() -> new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE));
    }

    private static int getVectorValue(final int distance) {
        if (distance == 0) {
            return 0;
        }
        return distance / Math.abs(distance);
    }

    public static Boolean isWhiteForward(final MoveDirection moveDirection) {
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP).contains(moveDirection);
    }

    public static Boolean isBlackForward(final MoveDirection moveDirection) {
        return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN).contains(moveDirection);
    }

    public int getXVector() {
        return vector.get(0);
    }

    public int getYVector() {
        return vector.get(1);
    }
}
