package chess.domain.board;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();
    private XPosition xPosition;
    private YPosition yPosition;

    static {
        for (XPosition xposition : XPosition.values()) {
            putPositionWithY(xposition);
        }
    }

    private static void putPositionWithY(XPosition xposition) {
        for (YPosition yPosition : YPosition.values()) {
            String positionKey = String.format("%c%d", xposition.getValue(),
                yPosition.getValue());
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
        return CACHE.get(positionKey);
    }

    public static Position of(char xRawPosition, int yRawPosition) {
        String positionKey = String.format("%c%d", xRawPosition,
            yRawPosition);
        return from(positionKey);
    }

    public static Position of(XPosition xPosition, YPosition yPosition) {
        char xRawPosition = xPosition.getValue();
        int yRawPosition = yPosition.getValue();
        return of(xRawPosition, yRawPosition);
    }

    public int computeHorizontalDistance(Position anotherPosition) {
        return xPosition.difference(anotherPosition.xPosition);
    }

    public int computeVerticalDistance(Position anotherPosition) {
        return yPosition.difference(anotherPosition.yPosition);
    }

    public Position computeSymmetricPosition() {
        return Position.of(xPosition.getValue(), 9 - yPosition.getValue());
    }

    public boolean sameYPosition(int rawYPosition) {
        return yPosition.samePosition(rawYPosition);
    }

    public void moveUnit(int xVector, int yVector) {
        xPosition = xPosition.moveUnit(xVector);
        yPosition = yPosition.moveUnit(yVector);
    }


    public boolean isLineMove(Position target) {
        return (computeHorizontalDistance(target) == 0 ||
            computeVerticalDistance(target) == 0);
    }

    public boolean isDiagonalMove(Position target) {
        return (Math.abs(computeHorizontalDistance(target)) ==
            Math.abs(computeVerticalDistance(target)));
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
