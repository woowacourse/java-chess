package controller;

import domain.Board;
import exception.GameFinishedException;
import view.Command;
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

        Board board = new Board();
        while (true) {
            Command command = inputView.getGameCommand();
            try {
                command.execute(board);
                printBoardStatus(board);
            } catch (GameFinishedException ignored) {
                break;
            }
        }
    }

    private void printBoardStatus(Board board) {
        outputView.printStatus(board.findCurrentStatus());
    }
}
