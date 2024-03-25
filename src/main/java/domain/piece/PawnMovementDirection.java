package domain.piece;

import domain.board.Position;

import java.util.Arrays;

import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;

public enum PawnMovementDirection implements MovementDirection {
    UP_ONE_STEP(-1, 0, WHITE),
    UP_TWO_STEP(-2, 0, WHITE),
    UP_RIGHT(-1, 1, WHITE),
    UP_LEFT(-1, -1, WHITE),
    DOWN_ONE_STEP(1, 0, BLACK),
    DOWN_TWO_STEP(2, 0, BLACK),
    DOWN_RIGHT(1, 1, BLACK),
    DOWN_LEFT(1, -1, BLACK);

    private final int rowDistance;
    private final int columnDistance;
    private final PieceColor pieceColor;

    PawnMovementDirection(final int rowDistance, final int columnDistance, final PieceColor pieceColor) {
        this.rowDistance = rowDistance;
        this.columnDistance = columnDistance;
        this.pieceColor = pieceColor;
    }

    public static PawnMovementDirection calculateDirection(final PieceColor pieceColor, final Position source, final Position destination) {
        final int rowDifference = destination.rowIndex() - source.rowIndex();
        final int columnDifference = destination.columnIndex() - source.columnIndex();

        return Arrays.stream(values())
                .filter(knightMovementDirection -> knightMovementDirection.matchDistance(pieceColor, rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(pieceColor.name() + "색상의 폰이 이동할 수 없는 방향입니다."));
    }

    private boolean matchDistance(final PieceColor pieceColor, final int rowDistance, final int columnDistance) {
        return this.pieceColor == pieceColor
                && this.rowDistance == rowDistance
                && this.columnDistance == columnDistance;
    }

    public boolean isCrossStep() {
        return this == UP_RIGHT || this == UP_LEFT
                || this == DOWN_RIGHT || this == DOWN_LEFT;
    }

    public boolean isTwoStep() {
        return this == UP_TWO_STEP || this == DOWN_TWO_STEP;
    }

    public PawnMovementDirection convertOneStep() {
        if (!this.isTwoStep() && !this.isOneStep()) {
            throw new IllegalArgumentException("전진 방향만 변환할 수 있습니다.");
        }

        if (this == UP_TWO_STEP) {
            return UP_ONE_STEP;
        }

        if (this == DOWN_TWO_STEP) {
            return DOWN_ONE_STEP;
        }

        return this;
    }

    private boolean isOneStep() {
        return this == UP_ONE_STEP || this == DOWN_ONE_STEP;
    }

    @Override
    public int getRowDistance() {
        return rowDistance;
    }

    @Override
    public int getColumnDistance() {
        return columnDistance;
    }
}
