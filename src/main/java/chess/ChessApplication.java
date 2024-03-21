package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGame chessGame = new ChessGame(inputView, outputView);

        try {
            chessGame.start();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
        }
    }
}
