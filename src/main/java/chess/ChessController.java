package chess;

import chess.domain.command.Command;
import chess.domain.state.State;

public class ChessController {
    private State state;

    public ChessController(final State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void progress(Command command) {
        state = command.changeChessState(state);
    }

    public State state() {
        return state;
    }
}
