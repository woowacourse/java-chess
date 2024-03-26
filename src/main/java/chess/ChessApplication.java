package chess;

import chess.controller.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(InputView.getInstance(), OutputView.getInstance());
        chessGame.run();
    }
}
