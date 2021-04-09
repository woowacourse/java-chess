package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Position {

    public static final String OUT_OF_BOUND_MESSAGE = "움직일려는 좌표가 보드판을 넘어갑니다.";
    public static final int OPPOSITE_SIDE_OF_BOARD = 9;

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    private final XPosition xPosition;
    private final YPosition yPosition;

    static {
        for (XPosition xposition : XPosition.values()) {
            putPositionWithY(xposition);
        }
    }

    private static void putPositionWithY(XPosition xposition) {
        for (YPosition yPosition : YPosition.values()) {
            String positionKey = positionKey(xposition.getValue(), yPosition.getValue());
            CACHE.put(positionKey, new Position(xposition, yPosition));
        }
    }

    public Position(Position position) {
        this.xPosition = position.xPosition;
        this.yPosition = position.yPosition;
    }

    private Position(XPosition xPosition, YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Position from(String positionKey) {
        return Optional
            .ofNullable(CACHE.get(positionKey))
            .orElseGet(() -> {
                throw new InvalidMoveException(Position.OUT_OF_BOUND_MESSAGE);
            });
    }

    public static Position of(char xRawPosition, int yRawPosition) {
        String positionKey = positionKey(xRawPosition, yRawPosition);
        return from(positionKey);
    }

    private static String positionKey(char xRawPosition, int yRawPosition) {
        return String.format("%c%d", xRawPosition, yRawPosition);
    }

    public static Position of(XPosition xPosition, YPosition yPosition) {
        char xRawPosition = xPosition.getValue();
        int yRawPosition = yPosition.getValue();
        return of(xRawPosition, yRawPosition);
    }

    public boolean isLineMove(Position target) {
        return (computeHorizontalDistance(target) == 0 ||
            computeVerticalDistance(target) == 0);
    }

    public boolean isDiagonalMove(Position target) {
        return (Math.abs(computeHorizontalDistance(target)) ==
            Math.abs(computeVerticalDistance(target)));
    }

    public int computeHorizontalDistance(Position anotherPosition) {
        return xPosition.difference(anotherPosition.xPosition);
    }

    public int computeVerticalDistance(Position anotherPosition) {
        return yPosition.difference(anotherPosition.yPosition);
    }

    public Position computeSymmetricPosition() {
        return Position.of(xPosition.getValue(), OPPOSITE_SIDE_OF_BOARD - yPosition.getValue());
    }

    public boolean sameYPosition(int rawYPosition) {
        return yPosition.samePosition(rawYPosition);
    }

    public Position movedPosition(int xVector, int yVector) {
        XPosition newXPosition = xPosition.moveUnit(xVector);
        YPosition newYPosition = yPosition.moveUnit(yVector);
        return Position.of(newXPosition, newYPosition);
    }

    @Override
    public String toString() {
        return String.format("%c%d", xPosition.getValue(), yPosition.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPosition == position.xPosition && yPosition == position.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}
