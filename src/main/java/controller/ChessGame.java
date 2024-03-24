package controller;

import controller.command.Command;
import controller.command.Ready;
import domain.board.Board;
import domain.board.BoardInitiator;
import view.InputView;
import view.OutputView;

public class ChessGame {
    public void start() {
        final Board board = new Board(BoardInitiator.init());
        Command command = new Ready();

        OutputView.printGameStartMessage();

        while (command.isNotEnd()) {
            command = command.execute(board, InputView::inputCommand);
        }

    }
}
