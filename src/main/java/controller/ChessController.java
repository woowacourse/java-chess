package controller;

import domain.Board;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final Board board;
    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(Board board, InputView inputview, OutputView outputView) {
        this.board = board;
        this.inputView = inputview;
        this.outputView = outputView;
    }

    public void initializeBoard() {
        outputView.printAskingBootingCommandMessage();
        Command command = inputView.getGameCommand();
        if (command.isStarting()) {
            board.initialize();
            printBoardStatus();
        }
    }

    public void movePiece() {
        try {
            Command command = inputView.getGameCommand();
            command.execute(board);
            printBoardStatus();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    private void printBoardStatus() {
        outputView.printStatus(board.findCurrentStatus());
    }
}
