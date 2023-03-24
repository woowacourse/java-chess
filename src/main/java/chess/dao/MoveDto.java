package chess.dao;

import chess.model.position.Position;

public class MoveDto {

    private final String source;
    private final String target;

    public MoveDto(final Position source, final Position target) {
        this.source = source.getPosition();
        this.target = target.getPosition();
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
