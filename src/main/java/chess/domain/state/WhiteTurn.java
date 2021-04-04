package chess.domain.state;

import chess.domain.piece.Color;

public class WhiteTurn implements State {

    @Override
    public Color color() {
        return Color.WHITE;
    }

    @Override
    public State opposite() {
        return new BlackTurn();
    }

    @Override
    public State end() {
        return new End();
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

    @Override
    public State finish() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
