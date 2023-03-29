package chess;

import chess.controller.ChessController;
import chess.history.GameHistory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    
    public static void main(String[] args) {
        GameHistory gameHistory = GameHistory.create();
        ChessController chessController = new ChessController(new InputView(), new OutputView());
        int gameID = chessController.selectGame(gameHistory);
        chessController.run(gameID);
    }
}
