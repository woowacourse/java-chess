package chess;

import chess.controller.ChessController;
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ChessGame chessGame = new ChessGame();
        final ChessController controller = new ChessController(inputView, outputView, chessGame);
        controller.run();
    }
}
