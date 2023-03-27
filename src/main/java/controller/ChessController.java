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
        controlGame(board);
    }

    private void controlGame(Board board) {
        try {
            Command command = inputView.getGameCommand();
            command.execute(board);
            printBoardStatus(board);
        } catch (GameFinishedException e) {
            return;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }

        controlGame(board);
    }

    private void printBoardStatus(Board board) {
        outputView.printStatus(board.findCurrentStatus());
    }
}
