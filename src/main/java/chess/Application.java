package chess;

import chess.controller.ChessController;
import chess.repository.JdbcBoardDao;
import chess.repository.ProdConnector;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new OutputView(), new InputView(), new JdbcBoardDao(new ProdConnector()));
        chessController.run();
    }
}
