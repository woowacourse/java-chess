package domain.piece;

import domain.board.Position;

import java.util.Arrays;

public enum KnightMovementDirection implements MovementDirection {
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

    public static KnightMovementDirection calculateDirection(final Position source, final Position destination) {
        final int rowDifference = destination.rowIndex() - source.rowIndex();
        final int columnDifference = destination.columnIndex() - source.columnIndex();

        return Arrays.stream(values())
                .filter(knightMovementDirection -> knightMovementDirection.matchDistance(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("나이트가 이동할 수 없는 방향입니다."));
    }

    private boolean matchDistance(final int rowDistance, final int columnDistance) {
        return this.rowDistance == rowDistance && this.columnDistance == columnDistance;
    }

    @Override
    public int getRowDistance() {
        return rowDistance;
    }

    @Override
    public int getColumnDistance() {
        return columnDistance;
    }


    // TODO : 레거시
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
