package dto;

import domain.board.Position;

public record PositionDto(int row, int column) {

    public static PositionDto from(final Position position) {
        return new PositionDto(position.rowIndex(), position.columnIndex());
    }

    public static PositionDto emptyPosition() {
        return new PositionDto(0, 0);
    }
}
