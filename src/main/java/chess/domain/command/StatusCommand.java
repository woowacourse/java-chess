package chess.domain.command;

import chess.domain.state.State;

public class StatusCommand extends Command {

    @Override
    public State changeChessState(final State state) {
        return state;
    }
}
