package chess.controller;

import chess.domain.Command;
import chess.view.InputView;

public class ChessController {

    public void run() {
        Command command = InputView.inputCommand();

    }
}
