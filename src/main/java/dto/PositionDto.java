package dto;

import domain.position.Position;

public class PositionDto {

    private final int row;
    private final int column;

    public PositionDto(Position position) {
        this.row = position.getRow();
        this.column = position.getColumn();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
