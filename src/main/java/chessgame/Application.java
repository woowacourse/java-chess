package chessgame;

import chessgame.controller.ChessController;
import chessgame.service.ChessService;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ChessService chessService = new ChessService();
        final ChessController chessController = new ChessController(inputView, outputView, chessService);
        chessController.run();
    }
}
