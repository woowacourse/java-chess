package controller;

import domain.Board;
import view.BootingCommand;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputview, OutputView outputView) {
        this.inputView = inputview;
        this.outputView = outputView;
    }

    public void boot() {
        outputView.printAskingBootingCommandMessage();

        BootingCommand command = inputView.getGameBootingCommand();
        while (command.isStart()) {
            printBoardStatus();
            command = inputView.getGameBootingCommand();
        }
    }

    private void printBoardStatus() {
        Board board = Board.initialize();
        outputView.printStatus(board.findCurrentStatus());
    }
}
