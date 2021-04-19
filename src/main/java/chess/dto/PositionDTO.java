package chess.dto;

import chess.domain.board.Position;

public class PositionDTO {

    private final Position source;
    private final Position target;

    public PositionDTO(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
