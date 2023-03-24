package chess.dao;

import chess.model.position.Position;

public class Move {

    private final String source;
    private final String target;

    public Move(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public Move(final Position source, final Position target) {
        this(source.getPosition(), target.getPosition());
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
