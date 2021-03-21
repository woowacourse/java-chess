package chess.controller;

import chess.controller.command.CommandController;
import chess.domain.Chess;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        
        Chess chess = Chess.createWithEmptyBoard();
        while (!chess.isTerminated()) {
            final CommandController commandController = InputView.askCommand();
            chess = commandController.execute(chess);
        }
    }
}
