package chess.domain.state;

import chess.domain.piece.Color;

public class Play extends AbstractState {

    public Play() {
        this(Color.WHITE);
    }

    public Play(Color color) {
        super(color);
    }

    @Override
    public State nextState() {
        return new Finish(color);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public void nextTurn() {
        color = color.next();
    }

    @Override
    public Color winner() {
        return Color.BLANK;
    }

    @Override
    public void validateMove() {
    }
}
