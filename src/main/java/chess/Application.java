package chess;

import chess.controller.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public final class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGame chessGame = new ChessGame(inputView, outputView);
        chessGame.run();
    }
}
