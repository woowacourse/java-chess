package chess.web.dto;

import chess.domain.board.Position;

public class PositionDto {
    private final String position;

    public PositionDto(final String position) {
        this.position = position;
    }

    public static PositionDto from(final Position position) {
        final char name = position.getColumn().getName();
        final int number = position.getRow().getNumber();
        return new PositionDto("" + name + number);
    }

    public String getPosition() {
        return position;
    }
}
