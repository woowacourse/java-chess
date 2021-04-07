package chess.Dto;

import chess.domain.position.Position;

public class PathDto {

    private final String from;

    public PathDto(final String from) {
        this.from = from;
    }

    public Position getFrom() {
        return Position.of(from);
    }
}
