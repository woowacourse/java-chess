package controller;

import controller.command.Command;
import controller.command.EndOnCommand;
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
        outputView.printGameGuideMessage();
        final ChessBoard board = ChessBoardFactory.createInitialChessBoard();

        Command command = readStartCommandUntilValid();
        while (command.isNotEnded()) {
            command.execute(board, outputView);
            command = readCommandIfGameNotEnded(board);
        }
    }

    private Command readStartCommandUntilValid() {
        try {
            return inputView.readStartCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readStartCommandUntilValid();
        }
    }

    private Command readCommandIfGameNotEnded(final ChessBoard board) {
        if (board.isKingNotExist()) {
            return new EndOnCommand();
        }
        return readCommandUntilValid();
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
