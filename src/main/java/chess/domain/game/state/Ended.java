package chess.domain.game.state;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Ended implements State {

    @Override
    public State start() {
        return new Ended();
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final Position from, final Position to) {
        return new Ended();
    }

    @Override
    public State status() {
        return new Ended();
    }

    @Override
    public boolean isNotEnded() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public Color getTurn() {
        return Color.NONE;
    }
}
