package chess.controller;

import chess.Command;
import chess.view.InputView;

public class ChessController {
    public void run() {
        InputView.startGame();
        Command command = new Command(InputView.inputCommand());
    }
}
