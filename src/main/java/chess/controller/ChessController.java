package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.Ready;
import chess.domain.board.Board;
import chess.view.InputView;

public final class ChessController {

    public void run() {
        Command command = new Ready(new Board());
        while (!command.isExit()) {
            command = command.execute(InputView.askCommand());
        }
    }
}
