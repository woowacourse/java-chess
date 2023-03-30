package chess;

import chess.controller.ChessController;
import chess.dao.MysqlChessBoardDao;
import chess.dao.MysqlChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(
                new InputView(), new OutputView(), new MysqlChessGameDao(), new MysqlChessBoardDao()
        );
        chessController.run();
    }
}
