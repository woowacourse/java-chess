package chess;

import chess.controller.ChessGame;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGame chessGame = new ChessGame(inputView, outputView);
        chessGame.run();
    }
}
