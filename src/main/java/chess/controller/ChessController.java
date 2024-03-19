package chess.controller;

import chess.command.Command;
import chess.view.InputView;

public class ChessController {
    private final InputView inputView;

    public ChessController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        if (inputView.readStartCommand() == Command.START) {
            startGame();
        }
    }

    private void startGame() {
    }
}
