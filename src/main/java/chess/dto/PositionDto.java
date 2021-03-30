package chess.dto;

import chess.domain.board.position.Position;

public class PositionDto {

    private final String id;

    public PositionDto(final Position position) {
        this.id = position.toString();
    }

    public String getId() {
        return id;
    }
}
