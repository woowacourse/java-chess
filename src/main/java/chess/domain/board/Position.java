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
        return anotherPosition.getXPosition().getValue() - this.xPosition.getValue(); //TODO getter 줄이기
    }

    public int computeVerticalDistance(Position anotherPosition) {
        return anotherPosition.getYPosition().getValue() - this.yPosition.getValue();
    }

    public Position computeSymmetricPosition() {
        return Position.of(xPosition.getValue(), 9 - yPosition.getValue());
    }

    public XPosition getXPosition() {
        return this.xPosition;
    }

    public YPosition getYPosition() {
        return this.yPosition;
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
