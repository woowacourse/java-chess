package chess;

import chess.controller.ChessGameController;
import chess.database.ChessBoardDao;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new InputView(), new OutputView(), new ChessBoardDao());
        chessGameController.run();
    }
}
