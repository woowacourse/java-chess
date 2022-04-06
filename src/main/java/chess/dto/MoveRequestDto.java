package chess.dto;

import chess.domain.position.Position;

public class MoveRequestDto {

    private final String from;
    private final String to;

    public MoveRequestDto(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return Position.from(from);
    }

    public Position getTo() {
        return Position.from(to);
    }
}
