package chess;

import chess.controller.ChessController;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new InputView(), new OutputView(),
                new ChessService(new GameDao(), new PieceDao()));

        chessController.run();
    }
}
