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
        return new Finished();
    }

    @Override
    public State status() {
        return new Status();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException();
    }

    @Override
    public State finish() {
        return new Finished();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
