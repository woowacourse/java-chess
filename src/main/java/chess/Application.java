package chess;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        outputView.printGameStartMessage();

        String startOrEndInput = inputView.getStartOrEndInput(outputView);
        if (startOrEndInput.equals("start")) {
            ChessGame game = new ChessGame();
            outputView.printBoard(game.getBoard());
        }
        inputView.terminate();
    }
}
