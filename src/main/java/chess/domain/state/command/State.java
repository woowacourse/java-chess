package chess.domain.state.command;

import chess.domain.Command;

public interface State {
    State execute(Command command);
    boolean isFinish();
}
