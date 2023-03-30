package chessgame;

import chessgame.controller.ChessController;
import chessgame.db.DBBoardDao;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final DBBoardDao DBBoardDao = new DBBoardDao();
        final ChessController chessController = new ChessController(inputView, outputView);
        chessController.run(DBBoardDao);
    }
}
