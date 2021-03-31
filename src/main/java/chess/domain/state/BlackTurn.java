package chess.domain.state;

import chess.domain.piece.Color;

public class BlackTurn implements State {
    @Override
    public Color color() {
        return Color.BLACK;
    }

    @Override
    public State opposite() {
        return new WhiteTurn();
    }

    @Override
    public State end() {
        return null;
    }

    @Override
    public State status() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException();
    }
}
