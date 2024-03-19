package controller;

import view.Command;
import view.InputView;

public class ChessGame {
    private final InputView inputView;

    public ChessGame(final InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        Command command = inputView.readCommand();
    }
}
