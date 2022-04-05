package chess.domain.command;

import chess.domain.state.State;

public class EndCommand extends Command {

    @Override
    public State changeChessState(final State state) {
        return state.end();
    }

    @Override
    public String toString() {
        return "EndCommand{}";
    }
}
