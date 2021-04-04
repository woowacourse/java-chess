package chess.domain.player;

import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;

public abstract class Player {
    private State state;

    protected Player(final State state) {
        this.state = state;
    }

    public final boolean isFinish() {
        return state.isFinishedTurn();
    }

    public final void move(final Source source, final Target target, final State targetState) {
        this.state = this.state.move(source, target, targetState);
    }

    public final double calculateScore() {
        return state.calculateScore();
    }

    public final void toRunningState(final State anotherState) {
        this.state = this.state.toRunningTurn(anotherState);
    }

    public final void changeState(final State nextState) {
        this.state = nextState;
    }

    public final State getState() {
        return state;
    }

    public final Pieces getPieces() {
        return state.pieces();
    }

    public abstract String getName();
}
