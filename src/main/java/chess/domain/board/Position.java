package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Position {

    public static final String OUT_OF_BOUND_MESSAGE = "움직일려는 좌표가 보드판을 넘어갑니다.";

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();
    private XPosition xPosition;
    private YPosition yPosition;

    static {
        for (XPosition xposition : XPosition.values()) {
            putPositionWithY(xposition);
        }
    }

    private static void putPositionWithY(final XPosition xposition) {
        for (YPosition yPosition : YPosition.values()) {
            String positionKey = String.format("%c%d", xposition.getValue(),
                yPosition.getValue());
            CACHE.put(positionKey, new Position(xposition, yPosition));
        }
    }

    public Position(final Position position) {
        this.xPosition = position.xPosition;
        this.yPosition = position.yPosition;
    }

    private Position(final XPosition xPosition, final YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Position from(final String positionKey) {
        return Optional
            .ofNullable(CACHE.get(positionKey))
            .orElseGet(() -> {
                throw new InvalidMoveException(Position.OUT_OF_BOUND_MESSAGE);
            });
    }

    public static Position of(final char xRawPosition, final int yRawPosition) {
        String positionKey = String.format("%c%d", xRawPosition,
            yRawPosition);
        return from(positionKey);
    }

    public static Position of(final XPosition xPosition, final YPosition yPosition) {
        char xRawPosition = xPosition.getValue();
        int yRawPosition = yPosition.getValue();
        return of(xRawPosition, yRawPosition);
    }

    public boolean isLineMove(final Position target) {
        return (computeHorizontalDistance(target) == 0 ||
            computeVerticalDistance(target) == 0);
    }

    public boolean isDiagonalMove(final Position target) {
        return (Math.abs(computeHorizontalDistance(target)) ==
            Math.abs(computeVerticalDistance(target)));
    }

    public int computeHorizontalDistance(final Position anotherPosition) {
        return xPosition.difference(anotherPosition.xPosition);
    }

    public int computeVerticalDistance(final Position anotherPosition) {
        return yPosition.difference(anotherPosition.yPosition);
    }

    public Position computeSymmetricPosition() {
        return Position.of(xPosition.getValue(), 9 - yPosition.getValue());
    }

    public boolean sameYPosition(final int rawYPosition) {
        return yPosition.samePosition(rawYPosition);
    }

    public void moveUnit(final int xVector, final int yVector) {
        xPosition = xPosition.moveUnit(xVector);
        yPosition = yPosition.moveUnit(yVector);
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
