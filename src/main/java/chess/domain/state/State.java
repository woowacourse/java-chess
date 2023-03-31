package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;

public abstract class State {
    private final Color color;

    protected State(final Color color) {
        this.color = color;
    }

    public abstract State move(Position source, Position target, Board board);

    public abstract State start();

    public abstract State end();

    protected final Color color() {
        return color;
    }

    public Color getColor() {
        return color;
    }
}
