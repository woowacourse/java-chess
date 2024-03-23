package controller;

import controller.command.Command;
import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final ChessBoard board = ChessBoardFactory.createInitialChessBoard();
        outputView.printGameStartMessage();

        Command command = readCommandUntilValid();
        while (command.isNotEnded()) {
            command.execute(board, outputView);
            command = readCommandUntilValid();
        }
    }

    private Command readCommandUntilValid() {
        try {
            return inputView.readCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readCommandUntilValid();
        }
    }
}
