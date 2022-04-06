package chess.dto;

import chess.domain.Position;

public class PositionDto {

    private final String position;

    private PositionDto(final String position) {
        this.position = position;
    }

    public static PositionDto toDto(final Position position) {
        return new PositionDto(String.valueOf(position.getColumn()) + position.getRow());
    }

    public String getPosition() {
        return position;
    }
}
