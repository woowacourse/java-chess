package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ChessController chessController = new ChessController(inputView, outputView);
        chessController.runChess();
    }
}
