package chess.controller.status;

import chess.controller.Command;

public interface Status {
    Status checkCommand(final Command command);

    boolean isRun();
}
