package chess;

import chess.controller.ChessController2;
import chess.history.GameHistory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    
    public static void main(String[] args) {
        GameHistory gameHistory = GameHistory.create();
        ChessController2 chessController2 = new ChessController2(new InputView(), new OutputView());
        int gameID = chessController2.selectGame(gameHistory);
        chessController2.run(gameID);
    }
}
