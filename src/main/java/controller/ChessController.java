package controller;

import domain.Board;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Command command;
        do {
            command = Command.of(inputView.readCommand());
            printIfCommandIsStart(command);
        } while (command.isStart());
    }

    private void printIfCommandIsStart(Command command) {
        if (command.isStart()) {
            Board board = new Board();
            outputView.printBoard(board);
        }
    }
}
