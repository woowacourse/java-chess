package chess.domain.state;

import chess.domain.piece.Color;

public class Finished implements State{

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
    public State status() {
        return null;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException();
    }
}
