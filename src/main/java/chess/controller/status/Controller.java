package chess.controller.status;

import chess.controller.Command;

public interface Controller {
    Controller checkCommand(final Command command, final Runnable runnable);

    boolean isRun();
}
