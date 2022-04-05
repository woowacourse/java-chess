package chess.dto;

import chess.position.Position;

public class PositionDto {

    private final String position;

    public PositionDto(final String position) {
        this.position = position;
    }

    public static PositionDto toDto(final Position position) {
        final String column = String.valueOf(position.getColumn().getValue());
        final String row = String.valueOf(position.getRow().getValue());
        return new PositionDto(column + row);
    }

    public String getPosition() {
        return position;
    }
}
