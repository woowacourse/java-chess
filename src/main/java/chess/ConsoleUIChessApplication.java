package chess;

import chess.consolecontroller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        ChessController.run(InputView::inputCommand, OutputView::showAllCommand);
    }
}
