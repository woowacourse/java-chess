package chess.domain.player;

import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;

public abstract class Player {
    private State state;

    protected Player(final State state) {
        this.state = state;
    }

    public final State getState() {
        return state;
    }

    public final boolean isFinish() {
        return state.isFinish();
    }

    public final void move(Source source, Target target, State targetState) {
        this.state = this.state.move(source, target, targetState);
    }

    public final void toRunningState(State anotherState){
        this.state = this.state.toRunningState(anotherState);
    }
}
