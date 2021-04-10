package chess.dto;

import chess.domain.board.position.Position;

public class MovableRequestDto {

    private final String id;

    public MovableRequestDto(final String id) {
        this.id = id;
    }

    public Position position() {
        return Position.of(this.id);
    }
}
