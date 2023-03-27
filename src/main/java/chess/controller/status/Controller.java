package chess.controller.status;

import chess.controller.Command;

public interface Controller {
    Controller checkCommand(final Command command);

    boolean isRun();

    void printBoard();
}
