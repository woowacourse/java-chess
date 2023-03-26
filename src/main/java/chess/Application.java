package chess;

import chess.controller.ChessController;
import chess.dao.DbChessBoardDao;
import chess.dao.DbChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(
                new InputView(), new OutputView(), new DbChessGameDao(), new DbChessBoardDao()
        );
        chessController.run();
    }
}
