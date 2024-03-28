package chess;

import chess.controller.ChessGameController;
import chess.db.ConnectionManager;
import chess.service.BoardService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new OutputView(), new InputView(),
                new BoardService(ConnectionManager.getProduction()));
        chessGameController.run();
    }
}
