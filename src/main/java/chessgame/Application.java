package chessgame;

import chessgame.controller.ChessController;
import chessgame.view.InputView;
import chessgame.view.OutputView;
import dao.BoardDao;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final BoardDao boardDao = new BoardDao();
        final ChessController chessController = new ChessController(inputView, outputView, boardDao);
        chessController.run();
    }
}
