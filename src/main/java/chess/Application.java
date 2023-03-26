package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new OutputView(), new InputView(),
                new ChessGameService(new ChessGame(), new ChessGameJdbcDao()));
        chessController.run();
    }
}
