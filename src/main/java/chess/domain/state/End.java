package chess.domain.state;

import chess.domain.piece.Color;

public class End implements State {
    private static final End END = new End();

    public static End getInstance() {
        return END;
    }

    private End() {
    }

    @Override
    public Color color() {
        throw new IllegalArgumentException();
    }

    @Override
    public State opposite() {
        throw new IllegalArgumentException();
    }

    @Override
    public State end() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

}
