package chess.domain.state;

import chess.domain.piece.Color;

public class End implements State {

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
