package controller;

import domain.Board;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.Optional;

public class ChessController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputview, OutputView outputView) {
        this.inputView = inputview;
        this.outputView = outputView;
    }

    public Optional<Board> makeBoard() {
        outputView.printAskingBootingCommandMessage();
        Command command = inputView.getGameCommand();
        if (command.isStarting()) {
            Board board = new Board();
            board.initialize();
            printBoardStatus(board);
            return Optional.of(board);
        }
        return Optional.empty();
    }

    public void movePiece(Board board) {
        try {
            Command command = inputView.getGameCommand();
            command.execute(board);
            printBoardStatus(board);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    private void printBoardStatus(Board board) {
        outputView.printStatus(board.findCurrentStatus());
    }
}
