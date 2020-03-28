package chess.domain.chesspieces;

import chess.domain.position.Position;

import java.util.Objects;

public abstract class Square {
    protected final String display;

    public Square(String display) {
        Objects.requireNonNull(display);
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public abstract boolean movable(Position from, Position to);

    public abstract boolean isSamePlayer(Square target);
}
