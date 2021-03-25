package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.UnableMoveTypeException;

import java.util.Arrays;

public enum MoveDirection {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, 1),
    RIGHT_UP(1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_DOWN(1, -1);

    private int x;
    private int y;

    MoveDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static MoveDirection getDirection(Position source, Position target) {
        int horizontalDistance = source.computeHorizontalDistance(target);
        int verticalDistance = source.computeVerticalDistance(target);

        int horizontalVector = getVectorValue(horizontalDistance);
        int verticalVector = getVectorValue(verticalDistance);

        return matchVectors(horizontalVector, verticalVector);
    }

    private static MoveDirection matchVectors(int horizontalVector, int verticalVector) {
        return Arrays.stream(values())
            .filter(element -> element.x == horizontalVector && element.y == verticalVector)
            .findAny()
            .orElseThrow(UnableMoveTypeException::new);
    }

    private static int getVectorValue(int distance) {
        if (distance == 0) {
            return 0;
        }
        return distance / Math.abs(distance);
    }

    public static Boolean isWhiteForward(MoveDirection moveDirection) {
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP).contains(moveDirection);
    }

    public static Boolean isBlackForward(MoveDirection moveDirection) {
        return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN).contains(moveDirection);
    }

    public int getXVector() {
        return x;
    }

    public int getYVector() {
        return y;
    }
}
