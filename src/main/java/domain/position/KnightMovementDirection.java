package domain.position;

import java.util.Arrays;

public enum KnightMovementDirection {
    UP_RIGHT(-2, 1),
    UP_LEFT(-2, -1),
    DOWN_RIGHT(2, 1),
    DOWN_LEFT(2, -1),
    RIGHT_UP(-1, 2),
    RIGHT_DOWN(1, 2),
    LEFT_UP(-1, -2),
    LEFT_DOWN(1, -2);

    private final int rowDistance;
    private final int columnDistance;

    KnightMovementDirection(final int rowDistance, final int columnDistance) {
        this.rowDistance = rowDistance;
        this.columnDistance = columnDistance;
    }

    public static boolean isMovableDirection(final Position source, final Position destination) {
        return Arrays.stream(values())
                .anyMatch(knightDirection -> knightDirection.find(source, destination));
    }

    private boolean find(final Position source, final Position destination) {
        final int rowDistance = destination.rowIndex() - source.rowIndex();
        final int columnDistance = destination.columnIndex() - source.columnIndex();

        return this.rowDistance == rowDistance && this.columnDistance == columnDistance;
    }
}
