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
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
