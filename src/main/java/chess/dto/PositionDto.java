package chess.dto;

import chess.domain.Position;

public class PositionDto {

    private final char column;
    private final char row;

    private PositionDto(final char column, final char row) {
        this.column = column;
        this.row = row;
    }

    public static PositionDto toDto(final Position position) {
        return new PositionDto(position.getColumn(), position.getRow());
    }

    public char getColumn() {
        return column;
    }

    public char getRow() {
        return row;
    }
}
