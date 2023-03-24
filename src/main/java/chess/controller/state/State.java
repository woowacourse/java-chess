package chess.controller.state;

import chess.controller.Command;

public interface State {
    State checkCommand(final Command command);

    boolean isRun();
}
